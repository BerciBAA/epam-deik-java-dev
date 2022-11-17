package com.epam.training.ticketservice.screeningmanager;

import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import com.epam.training.ticketservice.roommanager.model.RoomDto;
import lombok.Builder;
import lombok.Value;
import org.joda.time.DateTime;

import java.util.Objects;


@Builder
@Value
public class Screening {

    MovieDto movie;
    RoomDto room;
    DateTime date;

    public static Screening createScreening(MovieDto movie, RoomDto room, DateTime date) {
        return Screening.builder()
                .movie(movie)
                .room(room)
                .date(date)
                .build();

    }


    @Override
    public String toString() {

        return movie
                + ", screened in room "
                + room.getRoomName()
                + ", at "
                + date.toString("yyyy-MM-dd HH:mm");
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
        return Objects.equals(movie, screening.movie)
                && Objects.equals(room, screening.room)
                && Objects.equals(date, screening.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, room, date);
    }
}
