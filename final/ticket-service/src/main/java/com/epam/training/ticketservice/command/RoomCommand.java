package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.accountmanager.AccountManager;
import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.roommanager.RoomService;
import com.epam.training.ticketservice.roommanager.model.RoomDto;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class RoomCommand {

    AccountManager accountManager;

    RoomService roomService;

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "create room")
    public void createRoom(String roomName, int row, int column) {
        roomService.createRoom(roomName, row, column);
    }

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "update room")
    public void updateRoom(String roomName, int row, int column) {
        roomService.updateRoom(roomName, row, column);
    }

    @ShellMethodAvailability("isAvailability")
    @ShellMethod(key = "delete room")
    public void deleteRoom(String roomName) {
        roomService.deleteRoom(roomName);
    }

    @ShellMethod(key = "list rooms")
    public String listRoom() {
        List<RoomDto> roomList = roomService.listRooms();
        if (roomList.isEmpty()) {
            return "There are no rooms at the moment";
        }
        return roomService.listRooms().toString().replaceAll("[\\[\\]]", "");
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
