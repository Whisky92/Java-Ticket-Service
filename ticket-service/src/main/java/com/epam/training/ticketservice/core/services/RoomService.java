package com.epam.training.ticketservice.core.services;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.model.RoomDto;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Optional<RoomDto> createRoom(String name, int rowCount, int columnCount);

    int updateRoom(String name, int rowCount, int columnCount);

    RoomDto convertEntityToDto(RoomEntity roomEntity);

    int deleteRoom(String name);

    List<RoomDto> getRoomList();

}
