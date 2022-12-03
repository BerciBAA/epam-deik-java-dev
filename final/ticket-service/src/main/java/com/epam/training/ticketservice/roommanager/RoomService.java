package com.epam.training.ticketservice.roommanager;

import com.epam.training.ticketservice.roommanager.model.RoomDto;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void createRoom(String roomName, int row, int column);

    void updateRoom(String roomName, int row, int column);

    void deleteRoom(String roomName);

    List<RoomDto> listRooms();

    Optional<RoomDto> getRoomByName(String roomName);

}
