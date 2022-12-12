package com.epam.training.ticketservice.core.services;

import com.epam.training.ticketservice.core.model.ScreeningDto;

import java.util.List;

public interface ScreeningService {

    String createScreening(String movieTitle, String roomName, String startTime);

    List<ScreeningDto> getScreeningList();

    int deleteScreening(String movieTitle, String roomName, String startTime);


}
