package com.epam.training.ticketservice.screeningmanager;

import com.epam.training.ticketservice.moviemanager.MovieService;
import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.moviemanager.persistence.MovieRepository;
import com.epam.training.ticketservice.roommanager.RoomService;
import com.epam.training.ticketservice.roommanager.model.RoomDto;
import com.epam.training.ticketservice.roommanager.persistence.Room;
import com.epam.training.ticketservice.roommanager.persistence.RoomRepository;
import com.epam.training.ticketservice.screeningmanager.model.ScreeningDto;
import com.epam.training.ticketservice.screeningmanager.persistence.Screening;
import com.epam.training.ticketservice.screeningmanager.persistence.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ScreeningServiceImp implements ScreeningService {

    RoomService roomService;

    MovieService movieService;

    MovieRepository movieRepository;

    RoomRepository roomRepository;

    ScreeningRepository screeningRepository;

    @Override
    public Optional<ScreeningDto> createScreening(String movieTitle, String roomName, String strDate) {

        Optional<Screening> screening = makeScreening(movieTitle, roomName, strDate);
        List<Screening> screeningList = screeningRepository.findAll();

        if (screening.isPresent()) {
            ScreeningDto screeningDto = convertEntityToDto(screening.get());
            if (!this.isOverlapping(screeningDto, 10) && !screeningList.contains(screening.get())) {
                screeningRepository.save(screening.get());
            }
            return Optional.of(screeningDto);
        }
        return Optional.empty();
    }

    @Override
    public void deleteScreening(String movieTitle, String roomName, String strDate) {
        Optional<Movie> movie = movieRepository.findByTitle(movieTitle);
        Optional<Room> room = roomRepository.findByRoomName(roomName);
        if (movie.isPresent() && room.isPresent()) {
            Optional<Screening> screening = screeningRepository
                    .findByMovieAndRoomAndDate(movie.get(), room.get(), strDate);
            screening.ifPresent(value -> screeningRepository.delete(value));
        }

    }

    @Override
    public List<ScreeningDto> listScreenings() {
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    public String screeningsListToString() {

        StringBuilder sb = new StringBuilder();
        for (Screening screening :
                screeningRepository.findAll()) {
            ScreeningDto screeningDto = convertEntityToDto(screening);
            sb.append(screeningDto);
            sb.append("\n");
        }
        return sb.toString();
    }

    private ScreeningDto convertEntityToDto(Screening screening) {

        Optional<MovieDto> movieDto = movieService.getMovieByName(screening.getMovie().getTitle());
        Optional<RoomDto> roomDto = roomService.getRoomByName(screening.getRoom().getRoomName());
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime date = formatter.parseDateTime(screening.getDate());

        //if (movieDto.isPresent() && roomDto.isPresent()){
        return ScreeningDto.createScreening(movieDto.get(), roomDto.get(), date);
        //}

    }


    public Optional<Screening> makeScreening(String movieTitle, String roomName, String strDate) {

        Optional<Movie> movie = movieRepository.findByTitle(movieTitle);
        Optional<Room> room = roomRepository.findByRoomName(roomName);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime date = formatter.parseDateTime(strDate);

        if (movie.isPresent() && room.isPresent()) {
            Screening screening = new Screening(movie.get(), room.get(), date.toString("yyyy-MM-dd HH:mm"));
            return Optional.of(screening);
        }
        return Optional.empty();
    }


    @Override
    public boolean isOverlapping(ScreeningDto screeningDto, int breakTime) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime endDate = new DateTime(screeningDto.getDate()
                .plusMinutes(screeningDto.getMovie().getLengthInMinutes()));
        Interval interval = new Interval(screeningDto.getDate(), endDate);
        List<Screening> screeningList = screeningRepository.findAll();


        for (Screening currentScreening : screeningList) {


            DateTime startDate = formatter.parseDateTime(currentScreening.getDate());
            int movieLength = currentScreening.getMovie().getLengthInMinutes();
            DateTime currentEndDate = startDate.plusMinutes(movieLength + breakTime);
            Interval currentInterval = new Interval(startDate, currentEndDate);
            ScreeningDto currentScreeningDto = convertEntityToDto(currentScreening);

            if ((interval.overlaps(currentInterval)
                    && screeningDto.getRoom().getRoomName().equals(currentScreening.getRoom().getRoomName()))) {
                return !currentScreeningDto.equals(screeningDto);
            }
        }

        return false;
    }

}
