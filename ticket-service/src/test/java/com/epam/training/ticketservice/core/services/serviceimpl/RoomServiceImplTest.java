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
        Optional<MovieDTO> expected = Optional.empty();
        Mockito.when(roomRepository.findByName(TEST_ROOM.getName()))
                .thenReturn(Optional.empty());
        //When
        Optional<RoomDTO> actual = roomService
                .createRoom(TEST_ROOM.getName(), TEST_ROOM.getRowCount(), TEST_ROOM.getColumnCount());
        //Then
        Assertions.assertEquals(expected,actual);
        Mockito.verify(roomRepository).findByName(TEST_ROOM.getName());
    }


}
