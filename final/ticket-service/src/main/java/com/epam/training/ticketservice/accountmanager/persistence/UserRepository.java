package com.epam.training.ticketservice.accountmanager.persistence;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByUserNameAndUserPassword(String userName, String userPassword);

    Optional<User> findByUserName(String userName);
}