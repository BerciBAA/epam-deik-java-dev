package com.epam.training.ticketservice.roommanager;

import com.epam.training.ticketservice.moviemanager.MovieServiceImp;
import com.epam.training.ticketservice.moviemanager.model.MovieDto;
import com.epam.training.ticketservice.moviemanager.persistence.Movie;
import com.epam.training.ticketservice.moviemanager.persistence.MovieRepository;
import com.epam.training.ticketservice.roommanager.model.RoomDto;
import com.epam.training.ticketservice.roommanager.persistence.Room;
import com.epam.training.ticketservice.roommanager.persistence.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImpTest {
    private final RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
    private final RoomServiceImp underTest = new RoomServiceImp(roomRepository);
    Room TEST_ROOM = new Room("Test Room",10,10);
    private final RoomDto TEST_ROOM_DTO = RoomDto.createRoom("Test Room",10,10);
    @Test
    void testCreateRoom() {
        when(roomRepository.save(TEST_ROOM)).thenReturn(TEST_ROOM);

        underTest.createRoom(TEST_ROOM.getRoomName(),TEST_ROOM.getRows(),TEST_ROOM.getColumns());

        verify(roomRepository).save(TEST_ROOM);
    }
    @Test
    void testListRoomShouldReturnOneRoom() {
        when(roomRepository.findAll()).thenReturn(List.of(TEST_ROOM));
        List<RoomDto> expected = List.of(TEST_ROOM_DTO);

        List<RoomDto> actual = underTest.listRooms();
        underTest.createRoom(TEST_ROOM.getRoomName(),TEST_ROOM.getRows(),TEST_ROOM.getColumns());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testUpdateRoomTestRepositoryFindByTitle() {
        when(roomRepository.findByRoomName(TEST_ROOM.getRoomName())).thenReturn(Optional.ofNullable(TEST_ROOM));
        underTest.updateRoom(TEST_ROOM.getRoomName(),TEST_ROOM.getRows(),TEST_ROOM.getColumns());
        verify(roomRepository).findByRoomName(TEST_ROOM.getRoomName());
    }
    @Test
    void testUpdateRoomTestRepositorySave() {
        when(roomRepository.findByRoomName(TEST_ROOM.getRoomName())).thenReturn(Optional.ofNullable(TEST_ROOM));

        underTest.updateRoom(TEST_ROOM.getRoomName(),TEST_ROOM.getRows(),TEST_ROOM.getColumns());

        verify(roomRepository).save(TEST_ROOM);
    }
    @Test
    void testUpdateRoomTestRepositorySaveZeroTime() {
        when(roomRepository.findByRoomName("TEST")).thenReturn(Optional.empty());

        underTest.updateRoom("TEST",TEST_ROOM.getRows(),TEST_ROOM.getColumns());

        verify(roomRepository,times(0)).save(TEST_ROOM);
    }

    @Test
    void testDeleteRoomByNameTestMovieRepositoryFindByTitle() {
        when(roomRepository.findByRoomName(TEST_ROOM.getRoomName())).thenReturn(Optional.ofNullable(TEST_ROOM));

        underTest.deleteRoom(TEST_ROOM.getRoomName());

        verify(roomRepository).findByRoomName(TEST_ROOM.getRoomName());
    }

    @Test
    void testGetRoomByNameShouldReturnRoom() {

        Optional<RoomDto> expected = Optional.of(RoomDto.createRoom(TEST_ROOM.getRoomName(),TEST_ROOM.getRows(),TEST_ROOM.getColumns()));
        when(roomRepository.findByRoomName("Test Room")).thenReturn(Optional.ofNullable(TEST_ROOM));

        Optional<RoomDto> actual = underTest.getRoomByName(TEST_ROOM.getRoomName());
        Assertions.assertEquals(expected, actual);
        verify(roomRepository).findByRoomName("Test Room");
    }
}