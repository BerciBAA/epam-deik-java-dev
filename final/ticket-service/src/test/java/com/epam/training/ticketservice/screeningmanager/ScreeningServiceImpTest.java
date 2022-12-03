package com.epam.training.ticketservice.screeningmanager;

import com.epam.training.ticketservice.moviemanager.MovieService;
import com.epam.training.ticketservice.moviemanager.MovieServiceImp;
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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScreeningServiceImpTest {
    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

    private final RoomRepository roomRepository = Mockito.mock(RoomRepository.class);

    private final ScreeningRepository screeningRepository = Mockito.mock(ScreeningRepository.class);

    private final MovieService movieService = Mockito.mock(MovieService.class);

    private final RoomService roomService = Mockito.mock(RoomService.class);

    private final ScreeningService underTest = new ScreeningServiceImp(roomService, movieService,movieRepository,roomRepository,screeningRepository);

    Movie TEST_MOVIE = new Movie("Test Movie","Horror",120);

    Room TEST_ROOM = new Room("Test Room",10,10);

    private final RoomDto TEST_ROOM_DTO = RoomDto.createRoom("Test Room",10,10);

    private final MovieDto TEST_MOVIE_DTO = MovieDto.createMovie("Test Movie", "Horror", 120);

    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    DateTime dt = formatter.parseDateTime("2021-03-14 16:00");
    private final DateTime dateTime = new DateTime(dt);

    Screening TEST_SCREENING = new Screening(TEST_MOVIE, TEST_ROOM,"2021-03-14 16:00");

    private final ScreeningDto TEST_SCREENING_DTO = ScreeningDto.createScreening(TEST_MOVIE_DTO,TEST_ROOM_DTO,dateTime);
    @Test
    void testCreateScreeningShoudlCreateScreening() {
        when(screeningRepository.findAll()).thenReturn(List.of(TEST_SCREENING));
        Optional<ScreeningDto> expected = Optional.empty();
        Optional<ScreeningDto> actual = underTest.createScreening("Test Movie", "Test room","2021-03-14 16:00");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testCreateScreeningShouldReturnScreeningDTO() {
        when(screeningRepository.findAll()).thenReturn(List.of(TEST_SCREENING));
        when(movieRepository.findByTitle("Test Movie")).thenReturn(Optional.of(TEST_MOVIE));
        when(roomRepository.findByRoomName("Test Room")).thenReturn(Optional.of(TEST_ROOM));
        when(movieService.getMovieByName("Test Movie")).thenReturn(Optional.of(TEST_MOVIE_DTO));
        when(roomService.getRoomByName("Test Room")).thenReturn(Optional.of(TEST_ROOM_DTO));
        when(screeningRepository.save(TEST_SCREENING)).thenReturn(TEST_SCREENING);
        Optional<ScreeningDto> expected = Optional.of(TEST_SCREENING_DTO);
        Optional<ScreeningDto> actual = underTest.createScreening("Test Movie", "Test Room","2021-03-14 16:00");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteScreeningByMovieAndRoomAndDate() {
        when(movieRepository.findByTitle("Test Movie")).thenReturn(Optional.of(TEST_MOVIE));
        when(roomRepository.findByRoomName("Test Room")).thenReturn(Optional.of(TEST_ROOM));
        when(screeningRepository.findByMovieAndRoomAndDate(TEST_MOVIE,TEST_ROOM,"2021-03-14 16:00"))
                .thenReturn(Optional.of(TEST_SCREENING));
        underTest.deleteScreening("Test Movie","Test Room","2021-03-14 16:00");

        verify(screeningRepository).findByMovieAndRoomAndDate(TEST_MOVIE,TEST_ROOM,"2021-03-14 16:00");
        verify(movieRepository).findByTitle("Test Movie");
        verify(roomRepository).findByRoomName("Test Room");
    }

    @Test
    void testDeleteScreeningCallFindByTitleAndFIndByRoomName() {
        when(movieRepository.findByTitle("Test Movie")).thenReturn(Optional.empty());
        when(roomRepository.findByRoomName("Test Room")).thenReturn(Optional.empty());

        underTest.deleteScreening("Test Movie","Test Room","2021-03-14 16:00");

        verify(movieRepository).findByTitle("Test Movie");
        verify(roomRepository).findByRoomName("Test Room");
    }

    @Test
    void testListScreeningsShouldReturnScreeningDTOList() {
        when(screeningRepository.findAll()).thenReturn(List.of(TEST_SCREENING));
        when(movieRepository.findByTitle("Test Movie")).thenReturn(Optional.of(TEST_MOVIE));
        when(roomRepository.findByRoomName("Test Room")).thenReturn(Optional.of(TEST_ROOM));
        when(movieService.getMovieByName("Test Movie")).thenReturn(Optional.of(TEST_MOVIE_DTO));
        when(roomService.getRoomByName("Test Room")).thenReturn(Optional.of(TEST_ROOM_DTO));
        when(screeningRepository.save(TEST_SCREENING)).thenReturn(TEST_SCREENING);
        underTest.createScreening("Test Movie", "Test Room","2021-03-14 16:00");
        List<ScreeningDto> expected = List.of(TEST_SCREENING_DTO);
        List<ScreeningDto> actual = underTest.listScreenings();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void testIsOverlapping() {
        boolean expected = false;
        boolean actual = underTest.isOverlapping(TEST_SCREENING_DTO,0);
        Assertions.assertEquals(expected, actual);


    }

}