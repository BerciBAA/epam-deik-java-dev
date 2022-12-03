package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.accountmanager.AccountManager;
import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.screeningmanager.ScreeningService;
import com.epam.training.ticketservice.screeningmanager.model.ScreeningDto;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

    AccountManager accountManager;
    ScreeningService screeningService;

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "create screening")
    public String createScreening(String movieTitle, String roomName, String date) {
        Optional<ScreeningDto> screening = screeningService.createScreening(movieTitle, roomName, date);
        if (screening.isPresent()) {

            if (screeningService.isOverlapping(screening.get(), 0)) {

                return "There is an overlapping screening";
            }
            if (screeningService.isOverlapping(screening.get(), 10)) {
                return "This would start in the break period after another screening in this room";
            }

        }
        return null;
    }

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "delete screening")
    public void deleteScreening(String movieTitle, String roomName, String date) {
        screeningService.deleteScreening(movieTitle, roomName, date);
    }

    @ShellMethod(key = "list screenings")
    public String listScreening() {

        if (screeningService.listScreenings().isEmpty()) {
            return "There are no screenings";
        }
        return screeningService.screeningsListToString();
    }

    public Availability isAvailability() {
        Optional<UserDto> user = accountManager.getLoggedInUser();
        if (user.isPresent()) {
            if (user.get().isAdmin()) {
                return Availability.available();
            }
        }
        return Availability.unavailable("You are not signed in");
    }

}
