package com.epam.training.ticketservice.moviemanager;

import com.epam.training.ticketservice.moviemanager.model.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MoveService {

    void createMovie(String title, String genre, int lengthInMinute);

    void updateMovie(String title, String genre, int lengthInMinute);

    void deleteMovieByName(String title);

    Optional<MovieDto> getMovieByName(String title);

    List<MovieDto> listMovie();


}
