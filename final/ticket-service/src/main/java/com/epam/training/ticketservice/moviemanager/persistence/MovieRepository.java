package com.epam.training.ticketservice.moviemanager.persistence;

import com.epam.training.ticketservice.accountmanager.persistence.User;
import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Optional<Movie> findByTitle(String title);


}
