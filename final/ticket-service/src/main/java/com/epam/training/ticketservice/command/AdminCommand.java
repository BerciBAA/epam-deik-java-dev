package com.epam.training.ticketservice.command;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class AdminCommand {

    @ShellMethod(key = "sign in privileged")
    public String signInPrivileged(String userName, String password){
        return "";
    }
}
