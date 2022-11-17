package com.epam.training.ticketservice.accountmanager;


import lombok.AllArgsConstructor;
import org.jline.utils.Log;

import java.util.Optional;

@AllArgsConstructor
public class AccountManager {

    private final UserService userService;

    private Optional<User> user;

    public Optional<User> signInPrivileged(String userName, String userPassword){
        Optional<User> user = userService.getUser(userName,userPassword);
        this.user = user;
        return user;
    }

    public void signOut(){
        this.user = Optional.empty();
    }

    public Optional<User> describeAccount(){
        return user;
    }

    public boolean getLoggedIn(){
        return this.user.isEmpty();
    }
}
