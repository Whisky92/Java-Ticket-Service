package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private final RoomEntity TEST_ROOM = new RoomEntity("Room1",12,12);
    @Test
    void testCreateMovieShouldReturnOptionalEmptyWhenMovieTitleDoesNotExist() {
        //Given
        Optional<RoomDTO> expected = Optional.empty();
        Mockito.when(roomRepository.findByName(TEST_ROOM.getName()))
                .thenReturn(Optional.empty());
        //When
        Optional<RoomDTO> actual = roomService
                .createRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount());
        //Then
        Assertions.assertEquals(expected,actual);
        Mockito.verify(roomRepository).findByName(TEST_ROOM.getName());
    }

    @Test
    void testCreateMovieShouldReturnOptionalMovieWhenMovieTitleExists() {
        //Given
        Optional<RoomDTO> expected = Optional
                .of(new RoomDTO(TEST_ROOM.getName(),TEST_ROOM.getRowCount(),TEST_ROOM.getColumnCount()));
        Mockito.when(roomRepository.findByName(TEST_ROOM.getName()))
                .thenReturn(Optional.of(TEST_ROOM));
        //When
        Optional<RoomDTO> actual = roomService.createRoom(TEST_ROOM.getName(),TEST_ROOM.getRowCount(),TEST_ROOM.getColumnCount());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findByName(TEST_ROOM.getName());
    }

    @Test
    void testCreateMovieShouldReturnOneWhenTheMovieWasUpdated() {
        //Given
        int expected = 1;
        Mockito.when(roomRepository.updateRoom(TEST_ROOM.getName(),TEST_ROOM.getRowCount(),TEST_ROOM.getColumnCount()))
                .thenReturn(1);
        //When
        int actual = roomService.updateRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount());
        //Then
        Assertions.assertEquals(expected,actual);
        Mockito.verify(roomRepository)
                .updateRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount());
    }

    @Test
    void testCreateMovieShouldReturnZeroWhenTheMovieWasNotUpdated() {
        //Given
        int expected = 0;
        Mockito.when(roomRepository.updateRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount()))
                .thenReturn(0);
        //When
        int actual = roomService.updateRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository)
                .updateRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount());
    }

    @Test
    void testDeleteMovieShouldReturnOneWhenTheMovieWasDeleted() {
        //Given
        int expected = 1;
        Mockito.when(roomRepository.deleteByName(TEST_ROOM.getName()))
                .thenReturn(1);
        //When
        int actual = roomService.deleteRoom(TEST_ROOM.getName());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).deleteByName(TEST_ROOM.getName());

    }

    @Test
    void testDeleteMovieShouldReturnZeroWhenNoMovieWasDeleted() {
        //Given
        int expected = 0;
        Mockito.when(roomRepository.deleteByName(TEST_ROOM.getName()))
                .thenReturn(0);
        //When
        int actual = roomService.deleteRoom(TEST_ROOM.getName());
        //Then
        Assertions.assertEquals(expected,actual);
        Mockito.verify(roomRepository).deleteByName(TEST_ROOM.getName());

    }


}
