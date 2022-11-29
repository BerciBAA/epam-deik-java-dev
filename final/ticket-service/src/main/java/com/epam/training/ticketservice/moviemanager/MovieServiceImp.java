package com.epam.training.ticketservice.moviemanager;

import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.moviemanager.persistence.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieServiceImp implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public void createMovie(String title, String genre, int lengthInMinute) {
        List<Movie> movieList = movieRepository.findAll();
        Movie movie = new Movie(title,genre,lengthInMinute);
        if (!movieList.contains(movie)){
            movieRepository.save(movie);
        }
    }

    @Override
    public void updateMovie(String title, String genre, int lengthInMinute) {
        Optional<Movie> movie = movieRepository.findByTitle(title);
        if(movie.isPresent()){
            movie.get().setGenre(genre);
            movie.get().setLengthInMinutes(lengthInMinute);
            movieRepository.save(movie.get());
        }
    }

    @Override
    public void deleteMovieByName(String title) {
        Optional<Movie> movie = movieRepository.findByTitle(title);
        movie.ifPresent(value -> movieRepository.delete(value));
    }

    @Override
    public Optional<MovieDto> getMovieByName(String title) {
        return convertEntityToDto(movieRepository.findByTitle(title));
    }

    @Override
    public List<MovieDto> listMovie() {
        return movieRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return MovieDto.createMovie(movie.getTitle(),movie.getGenre(),movie.getLengthInMinutes());
    }

    private Optional<MovieDto> convertEntityToDto(Optional<Movie> movie) {
        return movie.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(movie.get()));
    }
}
