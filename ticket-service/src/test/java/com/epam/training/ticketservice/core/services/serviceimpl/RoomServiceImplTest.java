package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.repository.RoomRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private RoomServiceImpl roomService;

    private final RoomEntity TEST_ROOM = new RoomEntity("Room1",12,12);

}
