package com.epam.training.ticketservice.moviemanager;

import com.epam.training.ticketservice.accountmanager.UserService;
import com.epam.training.ticketservice.accountmanager.UserServiceImp;
import com.epam.training.ticketservice.accountmanager.persistence.UserRepository;
import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.moviemanager.persistence.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieServiceImpTest {
    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    private final MovieServiceImp underTest = new MovieServiceImp(movieRepository);
    Movie TEST_MOVIE = new Movie("Test Movie","Horror",120);
    private final MovieDto TEST_MOVIE_DTO = MovieDto.createMovie("Test Movie", "Horror", 120);

    @Test
    void createMovie() {

        when(movieRepository.save(TEST_MOVIE)).thenReturn(TEST_MOVIE);

        underTest.createMovie(TEST_MOVIE.getTitle(),TEST_MOVIE.getGenre(),TEST_MOVIE.getLengthInMinutes());

        verify(movieRepository).save(TEST_MOVIE);

    }

    @Test
    void createMovie2() {

        when(movieRepository.findAll()).thenReturn(List.of(TEST_MOVIE));
        List<MovieDto> expected = List.of(TEST_MOVIE_DTO);

        List<MovieDto> actual = underTest.listMovie();
        underTest.createMovie(TEST_MOVIE_DTO.getTitle(),TEST_MOVIE_DTO.getGenre(),TEST_MOVIE_DTO.getLengthInMinutes());

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovieByName() {
    }

    @Test
    void getMovieByName() {
    }

    @Test
    void listMovie() {
    }
}