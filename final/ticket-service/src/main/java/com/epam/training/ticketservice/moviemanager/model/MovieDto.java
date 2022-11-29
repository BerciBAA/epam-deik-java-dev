package com.epam.training.ticketservice.moviemanager.model;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Builder
@Value
public class MovieDto {
    String title;
    String genre;
    int lengthInMinutes;

    public static MovieDto createMovie(String title, String genre, int lengthInMinutes) {
        return MovieDto.builder()
                .title(title)
                .genre(genre)
                .lengthInMinutes(lengthInMinutes)
                .build();
    }


    @Override
    public String toString() {
        return title + " (" + genre + ", " + lengthInMinutes + " minutes)";

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDto movie = (MovieDto) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
