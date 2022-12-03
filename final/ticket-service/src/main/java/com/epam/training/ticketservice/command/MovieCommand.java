package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.accountmanager.AccountManagerImp;
import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.moviemanager.MovieService;
import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class MovieCommand {

    AccountManagerImp accountManager;

    MovieService movieService;

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "create movie")
    //@ShellMethodAvailability("isAvailable")
    public void createMovie(String title, String genre, int lengthInMinutes) {
        movieService.createMovie(title, genre, lengthInMinutes);
    }


    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "update movie")
    public void updateMovie(String title, String genre, int lengthInMinutes) {
        movieService.updateMovie(title, genre, lengthInMinutes);
    }

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "delete movie")
    public void deleteMovies(String title) {
        movieService.deleteMovieByName(title);
    }


    @ShellMethod(key = "list movies")
    public String listMovies() {
        List<MovieDto> movieList = movieService.listMovie();
        if (movieList.isEmpty()) {
            return "There are no movies at the moment";
        }
        return movieList.toString().replaceAll("[\\[\\]]", "");
    }

    public Availability isAvailability() {
        Optional<UserDto> user = accountManager.getLoggedInUser();
        if (user.isPresent()) {
            if (user.get().isAdmin()) {
                return Availability.available();
            }
        }
        return Availability.unavailable("You are not signed in");
    }


}
