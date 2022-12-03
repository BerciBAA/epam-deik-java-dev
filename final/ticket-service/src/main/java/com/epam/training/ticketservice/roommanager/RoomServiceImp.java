package com.epam.training.ticketservice.roommanager;

import com.epam.training.ticketservice.roommanager.model.RoomDto;
import com.epam.training.ticketservice.roommanager.persistence.Room;
import com.epam.training.ticketservice.roommanager.persistence.RoomRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
public class RoomServiceImp implements RoomService {

    RoomRepository roomRepository;

    @Override
    public void createRoom(String roomName, int row, int column) {
        Room room = new Room(roomName, row, column);
        List<Room> roomList = roomRepository.findAll();
        if (!roomList.contains(room)) {
            roomRepository.save(room);
        }
    }

    @Override
    public void updateRoom(String roomName, int row, int column) {

        Optional<Room> room = roomRepository.findByRoomName(roomName);
        if (room.isPresent()) {
            room.get().setRows(row);
            room.get().setColumns(column);
            roomRepository.save(room.get());
        }
    }

    @Override
    public void deleteRoom(String roomName) {
        Optional<Room> oldRoom = roomRepository.findByRoomName(roomName);
        oldRoom.ifPresent(room -> roomRepository.delete(room));
    }

    @Override
    public List<RoomDto> listRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoomDto> getRoomByName(String roomName) {
        return convertEntityToDto(roomRepository.findByRoomName(roomName));
    }

    private RoomDto convertEntityToDto(Room room) {
        return RoomDto.createRoom(room.getRoomName(), room.getRows(), room.getColumns());
    }

    private Optional<RoomDto> convertEntityToDto(Optional<Room> room) {
        return room.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(room.get()));
    }
}
