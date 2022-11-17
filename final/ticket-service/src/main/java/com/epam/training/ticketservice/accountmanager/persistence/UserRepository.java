package com.epam.training.ticketservice.accountmanager.persistence;


import java.util.Optional;

import com.epam.training.ticketservice.accountmanager.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);
}