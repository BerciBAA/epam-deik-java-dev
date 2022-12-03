package com.epam.training.ticketservice.accountmanager;

import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.accountmanager.persistence.User;
import com.epam.training.ticketservice.accountmanager.persistence.UserRepository;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImpTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService underTest = new UserServiceImp(userRepository);
    @Test
        void testGetUserByNameShouldReturnSameUsername() {
        User user = new User("user","pass",true);
        when(userRepository.findByUserName("user")).thenReturn(Optional.of(user));

        Optional<User> expected = Optional.of(user);
        Optional<UserDto> actual = underTest.getUserByName("user");

        assertEquals(actual.get().getUserName(),expected.get().getUserName());
        verify(userRepository).findByUserName("user");

    }

    @Test
    void testGetUserByNameShouldReturnEmptyUser() {

        when(userRepository.findByUserName("user")).thenReturn(Optional.empty());

        Optional<User> expected = Optional.empty();
        Optional<UserDto> actual = underTest.getUserByName("user");

        assertEquals(actual.isEmpty(),expected.isEmpty());
        verify(userRepository).findByUserName("user");

    }

    @Test
    void testCreateUserShouldCreateUser() {

        User user = new User("user","pass",true);
        when(userRepository.save(user)).thenReturn(user);
        underTest.createUser("user","pass",true);
        verify(userRepository).save(user);
    }

    @Test
    void testGetUserShouldReturnUser() {
        User user = new User("user","pass",true);
        when(userRepository.findByUserNameAndUserPassword("user","pass"))
                .thenReturn(Optional.of(user));

        Optional<User> expected = Optional.of(user);
        Optional<UserDto> actual = underTest.getUser("user","pass");

        assertEquals(actual.get().getUserName(),expected.get().getUserName());
        verify(userRepository).findByUserNameAndUserPassword("user","pass");
    }


    @Test
    void testGetUserShouldReturnEmptyUser() {
        when(userRepository.findByUserNameAndUserPassword("user","pass"))
                .thenReturn(Optional.empty());

        Optional<User> expected = Optional.empty();
        Optional<UserDto> actual = underTest.getUser("user","pass");

        assertEquals(actual.isEmpty(),expected.isEmpty());
        verify(userRepository).findByUserNameAndUserPassword("user","pass");
    }
}