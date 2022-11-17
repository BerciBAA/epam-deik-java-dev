package com.epam.training.ticketservice.moviemanager;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Builder
@Value
public class Movie {
    String title;
    String genre;
    int lengthInMinutes;

    public static Movie createMovie(String title, String genre, int lengthInMinutes) {
        return Movie.builder()
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
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
