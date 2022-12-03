package com.epam.training.ticketservice.accountmanager;

import com.epam.training.ticketservice.accountmanager.model.UserDto;

import java.util.Optional;

public interface AccountManager {
    Optional<UserDto> signInPrivileged(String userName, String userPassword);

    void signOut();

    Optional<UserDto> describeAccount();

    Optional<UserDto> getLoggedInUser();

    void signUp(String userName, String userPassword);

    Optional<UserDto> signIn(String userName, String userPassword);

}
