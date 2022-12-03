package com.epam.training.ticketservice.screeningmanager.persistence;

import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.roommanager.persistence.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    Optional<Screening> findByMovieAndRoomAndDate(Movie movie, Room room, String date);
}
