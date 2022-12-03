package com.epam.training.ticketservice.accountmanager;


import com.epam.training.ticketservice.accountmanager.model.UserDto;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AccountManagerImp implements AccountManager {

    UserService userService;

    UserDto user;


    public void signOut() {
        this.user = null;
    }

    public Optional<UserDto> describeAccount() {
        return Optional.ofNullable(this.user);
    }

    public Optional<UserDto> getLoggedInUser() {
        return Optional.ofNullable(this.user);
    }

    public void signUp(String userName, String userPassword) {
        userService.createUser(userName, userPassword, false);
    }

    public Optional<UserDto> signInPrivileged(String userName, String userPassword) {
        Optional<UserDto> user = userService.getUser(userName, userPassword);
        if (user.isPresent()) {
            if (user.get().isAdmin()) {
                this.user = user.get();
            }
        }
        return user;
    }

    public Optional<UserDto> signIn(String userName, String userPassword) {
        Optional<UserDto> user = userService.getUser(userName, userPassword);
        if (user.isPresent()) {
            if (!user.get().isAdmin()) {
                this.user = user.get();
            }
        }
        return user;
    }

}
