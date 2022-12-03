package com.epam.training.ticketservice.core.services.service;

import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.model.ScreeningDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScreeningService {
    String createScreening(String movieTitle, String roomName, String startTime);

    List<ScreeningDTO> getScreeningList();



}
