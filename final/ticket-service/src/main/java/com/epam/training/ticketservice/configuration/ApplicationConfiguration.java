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
import com.epam.training.ticketservice.screeningmanager.ScreeningService;
import com.epam.training.ticketservice.screeningmanager.ScreeningServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserService userService(UserRepository repository) {
        return new UserServiceImp(repository);
    }

    @Bean
    public AccountManager accountManager() {
        return new AccountManagerImp();
    }

    @Bean
    public MovieService movieService(MovieRepository repository) {
        return new MovieServiceImp(repository);
    }

    @Bean
    public RoomService roomService() {
        return new RoomServiceImp();
    }

    @Bean
    public ScreeningService screeningService() {
        return new ScreeningServiceImp();
    }

    @Bean
    public BookingService bookingService(){
        return new BookingServiceImp();
    }
}
