package com.epam.training.ticketservice.core.services.service;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.RoomDTO;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Optional<RoomDTO> createRoom(String name, int rowCount, int columnCount);

    int updateRoom(String name, int rowCount, int columnCount);
    RoomDTO convertEntityToDTO(RoomEntity roomEntity);

    int deleteRoom(String name);
    List<RoomDTO> getRoomList();

}
