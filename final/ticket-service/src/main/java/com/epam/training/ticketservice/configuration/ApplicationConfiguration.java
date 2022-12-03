package com.epam.training.ticketservice.configuration;

import com.epam.training.ticketservice.accountmanager.AccountManager;
import com.epam.training.ticketservice.accountmanager.AccountManagerImp;
import com.epam.training.ticketservice.accountmanager.UserService;
import com.epam.training.ticketservice.accountmanager.UserServiceImp;
import com.epam.training.ticketservice.accountmanager.persistence.UserRepository;
import com.epam.training.ticketservice.bookingmanager.BookingService;
import com.epam.training.ticketservice.bookingmanager.BookingServiceImp;
import com.epam.training.ticketservice.moviemanager.MovieService;
import com.epam.training.ticketservice.moviemanager.MovieServiceImp;
import com.epam.training.ticketservice.moviemanager.persistence.MovieRepository;
import com.epam.training.ticketservice.roommanager.RoomService;
import com.epam.training.ticketservice.roommanager.RoomServiceImp;
import com.epam.training.ticketservice.roommanager.persistence.RoomRepository;
import com.epam.training.ticketservice.screeningmanager.ScreeningService;
import com.epam.training.ticketservice.screeningmanager.ScreeningServiceImp;
import com.epam.training.ticketservice.screeningmanager.persistence.ScreeningRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserService userService(UserRepository repository) {
        return new UserServiceImp(repository);
    }

    @Bean
    public AccountManager accountManager(UserService userService) {
        return new AccountManagerImp(userService, null);
    }

    @Bean
    public MovieService movieService(MovieRepository repository) {
        return new MovieServiceImp(repository);
    }

    @Bean
    public RoomService roomService(RoomRepository repository) {
        return new RoomServiceImp(repository);
    }

    @Bean
    public ScreeningService screeningService(RoomService roomS,
                                             MovieService movieS,
                                             MovieRepository movieRepository, RoomRepository roomRepository,
                                             ScreeningRepository screeningRepository) {

        return new ScreeningServiceImp(roomS, movieS, movieRepository, roomRepository, screeningRepository);
    }

    @Bean
    public BookingService bookingService() {
        return new BookingServiceImp();
    }
}
