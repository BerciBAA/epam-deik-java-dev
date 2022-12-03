package com.epam.training.ticketservice.screeningmanager.persistence;

import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.roommanager.persistence.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Screening {


    @OneToOne
    Room room;
    @OneToOne
    Movie movie;
    String date;
    @Id
    @GeneratedValue
    private Long id;

    public Screening(Movie movie, Room room, String date) {

        this.movie = movie;
        this.room = room;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Screening screening = (Screening) o;
        return Objects.equals(room, screening.room)
                && Objects.equals(movie, screening.movie)
                && Objects.equals(date, screening.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, movie, date);
    }
}
