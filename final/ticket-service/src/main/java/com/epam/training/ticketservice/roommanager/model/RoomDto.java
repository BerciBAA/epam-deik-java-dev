package com.epam.training.ticketservice.roommanager.model;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class RoomDto {

    String roomName;
    int row;
    int column;

    public static RoomDto createRoom(String roomName, int row, int column) {
        return RoomDto.builder().roomName(roomName).row(row).column(column).build();
    }


    @Override
    public String toString() {
        return "Room " + roomName + " with " + row * column + " seats, " + row + " rows and " + column + " columns";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDto room = (RoomDto) o;
        return Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName);
    }
}
