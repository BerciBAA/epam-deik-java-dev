package com.epam.training.ticketservice.accountmanager;

import com.epam.training.ticketservice.accountmanager.model.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountManagerImpTest {
    private final UserService userService = Mockito.mock(UserService.class);
    private final AccountManagerImp underTest = new AccountManagerImp(userService,null);

    @Test
    void testDescribeAccountShouldReturnUserDTO() {
        Optional<UserDto> actual = underTest.describeAccount();
        assertEquals(Optional.empty(),actual);
    }


    @Test
    void testSignInPrivilegedShouldReturnLoggedInUserWithPrivilege() {

        UserDto expected = UserDto.createUser("admin",true);
        when(userService.getUser("admin","admin")).thenReturn(Optional.ofNullable(expected));
        Optional<UserDto> actual = underTest.signInPrivileged("admin", "admin");


        assertEquals(expected,actual.get());
    }

    @Test
    void testGetLoggedInUserShouldReturnUserDTO() {
        Optional<UserDto> actual = underTest.describeAccount();
        assertEquals(Optional.empty(),actual);
    }

}