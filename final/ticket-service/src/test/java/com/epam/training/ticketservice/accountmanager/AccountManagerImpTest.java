package com.epam.training.ticketservice.accountmanager;

import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.accountmanager.persistence.User;
import com.epam.training.ticketservice.moviemanager.MovieServiceImp;
import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.moviemanager.persistence.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountManagerImpTest {
    private final UserService userService = Mockito.mock(UserService.class);
    private final AccountManagerImp underTest = new AccountManagerImp(userService,null);

    @Test
    void describeAccount() {
        Optional<UserDto> actual = underTest.describeAccount();
        assertEquals(Optional.empty(),actual);
    }

    @Test
    void getLoggedInUser() {
        Optional<UserDto> actual = underTest.describeAccount();
        assertEquals(Optional.empty(),actual);
    }

    @Test
    void signInPrivileged() {

        UserDto expected = UserDto.createUser("admin",true);
        when(userService.getUser("admin","admin")).thenReturn(Optional.ofNullable(expected));
        Optional<UserDto> actual = underTest.signInPrivileged("admin", "admin");


        assertEquals(expected,actual.get());
    }

    @Test
    void signIn() {
    }
}