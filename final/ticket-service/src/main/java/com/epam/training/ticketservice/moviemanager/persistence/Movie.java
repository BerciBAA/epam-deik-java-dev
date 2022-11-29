package com.epam.training.ticketservice.moviemanager.persistence;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor

public class Movie {

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String title;
    private String genre;
    private int lengthInMinutes;

    public Movie(String title, String genre, int lengthInMinutes){
        this.title = title;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
