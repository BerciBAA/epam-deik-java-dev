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
public class AdminCommand {

    AccountManager accountManager;

    @ShellMethod(key = "sign in privileged")
    public String signInPrivileged(String userName, String userPassword) {
        Optional<UserDto> admin = accountManager.signInPrivileged(userName, userPassword);
        if (admin.isEmpty()) {
            return "Login failed due to incorrect credentials";
        }
        return null;
    }

    @ShellMethod(key = "sign out")
    public void signOut() {
        accountManager.signOut();
    }

    @ShellMethod(key = "describe account")
    public String describeAccount() {
        Optional<UserDto> user = accountManager.describeAccount();
        if (user.isPresent()) {
            if (user.get().isAdmin()){
                return "Signed in with privileged account " + "'" + user.get().getUserName() + "'";
            }
            return "Signed in with privileged account " + "'" + user.get().getUserName() + "'" +"USER LÉPETT BE MOST";

        }
        return "You are not signed in";
    }

    public Availability isAvailability() {

        Optional<UserDto> user = accountManager.getLoggedInUser();
        if (user.isPresent()){
            if (user.get().isAdmin()) {
                return Availability.available();
            }
        }
        return Availability.unavailable("You are not signed in");

    }

}
