package com.epam.training.ticketservice.configuration;

import javax.annotation.PostConstruct;

import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.accountmanager.persistence.User;
import com.epam.training.ticketservice.accountmanager.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class InMemoryDatabaseInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User admin = new User("admin","admin",true);
        userRepository.save(admin);

    }
}