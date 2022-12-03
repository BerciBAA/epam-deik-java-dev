package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.accountmanager.AccountManager;
import com.epam.training.ticketservice.accountmanager.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class UserCommand {

    AccountManager accountManager;

    @ShellMethod(key = "sign up")
    public void signUp(String userName, String userPassword) {
        accountManager.signUp(userName, userPassword);

    }

    @ShellMethod(key = "sign in")
    public String signIn(String userName, String userPassword) {
        Optional<UserDto> user = accountManager.signIn(userName, userPassword);
        if (user.isPresent()) {
            return "successful login";
        }
        return "Login failed due to incorrect credentials";
    }

    public Availability isAvailability() {
        Optional<UserDto> user = accountManager.getLoggedInUser();
        if (user.isPresent()) {
            if (!user.get().isAdmin()) {
                return Availability.available();
            }
        }
        return Availability.unavailable("Login failed due to incorrect credentials");

    }
}
