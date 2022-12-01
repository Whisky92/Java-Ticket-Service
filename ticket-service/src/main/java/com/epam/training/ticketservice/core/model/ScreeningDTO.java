package com.epam.training.ticketservice.core.model;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
public class ScreeningDTO{
    private final MovieDTO movieDTO;
    private final RoomDTO roomDTO;
    private final Date date;
}
