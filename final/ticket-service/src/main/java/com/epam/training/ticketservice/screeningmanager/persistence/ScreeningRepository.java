package com.epam.training.ticketservice.screeningmanager.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Scanner;

public interface Repository  extends JpaRepository<Screening,Integer> {

}
