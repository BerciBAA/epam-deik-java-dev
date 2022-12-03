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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceImpTest {
    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    private final MovieServiceImp underTest = new MovieServiceImp(movieRepository);
    Movie TEST_MOVIE = new Movie("Test Movie","Horror",120);
    private final MovieDto TEST_MOVIE_DTO = MovieDto.createMovie("Test Movie", "Horror", 120);

    @Test
    void testCreateMovie() {

        when(movieRepository.save(TEST_MOVIE)).thenReturn(TEST_MOVIE);

        underTest.createMovie(TEST_MOVIE.getTitle(),TEST_MOVIE.getGenre(),TEST_MOVIE.getLengthInMinutes());

        verify(movieRepository).save(TEST_MOVIE);

    }

    @Test
    void testListMovieShouldReturnOneMovie() {

        when(movieRepository.findAll()).thenReturn(List.of(TEST_MOVIE));
        List<MovieDto> expected = List.of(TEST_MOVIE_DTO);

        List<MovieDto> actual = underTest.listMovie();
        underTest.createMovie(TEST_MOVIE_DTO.getTitle(),TEST_MOVIE_DTO.getGenre(),TEST_MOVIE_DTO.getLengthInMinutes());

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testUpdateMovieTestRepositoryFindByTitle() {
        when(movieRepository.findByTitle(TEST_MOVIE.getTitle())).thenReturn(Optional.ofNullable(TEST_MOVIE));
        underTest.updateMovie(TEST_MOVIE.getTitle(),TEST_MOVIE.getGenre(),TEST_MOVIE.getLengthInMinutes());
        verify(movieRepository).findByTitle(TEST_MOVIE.getTitle());
    }
    @Test
    void testUpdateMovieTestRepositorySave() {

        when(movieRepository.findByTitle(TEST_MOVIE.getTitle())).thenReturn(Optional.ofNullable(TEST_MOVIE));

        underTest.updateMovie(TEST_MOVIE.getTitle(),TEST_MOVIE.getGenre(),TEST_MOVIE.getLengthInMinutes());

        verify(movieRepository).save(TEST_MOVIE);
    }
    @Test
    void testUpdateMovieTestRepositorySaveZeroTime() {

        when(movieRepository.findByTitle("TEST")).thenReturn(Optional.empty());

        underTest.updateMovie("TEST",TEST_MOVIE.getGenre(),TEST_MOVIE.getLengthInMinutes());

        verify(movieRepository,times(0)).save(TEST_MOVIE);
    }

    @Test
    void testDeleteMovieByNameTestMovieRepositoryFindByTitle() {

        when(movieRepository.findByTitle(TEST_MOVIE.getTitle())).thenReturn(Optional.ofNullable(TEST_MOVIE));

        underTest.deleteMovieByName(TEST_MOVIE.getTitle());

        verify(movieRepository).findByTitle(TEST_MOVIE.getTitle());
    }


    @Test
    void testGetMovieByNameShouldReturnMovie() {
        Optional<MovieDto> expected = Optional.empty();
        Optional<MovieDto> actual = underTest.getMovieByName("Test title");
        Assertions.assertEquals(expected, actual);

    }
    @Test
    void testGetMovieByNameShouldReturnOneMovieByName() {

        Optional<MovieDto> expected = Optional.of(MovieDto.createMovie("Test Movie", "Horror", 120));
        when(movieRepository.findByTitle("Test Movie")).thenReturn(Optional.ofNullable(TEST_MOVIE));

        Optional<MovieDto> actual = underTest.getMovieByName(TEST_MOVIE.getTitle());
        Assertions.assertEquals(expected, actual);
        verify(movieRepository).findByTitle("Test Movie");
    }

}