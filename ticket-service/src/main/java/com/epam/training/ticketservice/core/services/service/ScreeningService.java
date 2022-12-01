package com.epam.training.ticketservice.core.services.service;

import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.model.ScreeningDTO;

import java.util.Date;
import java.util.Optional;

public interface ScreeningService {
    Optional<ScreeningDTO> createService(MovieDTO movie, RoomDTO room, Date startTime);

}
