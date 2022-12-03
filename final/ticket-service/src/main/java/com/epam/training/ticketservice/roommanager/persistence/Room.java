package com.epam.training.ticketservice.roommanager.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String roomName;
    private int rows;
    private int columns;

    public Room(String roomName, int row, int column) {
        this.roomName = roomName;
        this.rows = row;
        this.columns = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName);
    }
}
