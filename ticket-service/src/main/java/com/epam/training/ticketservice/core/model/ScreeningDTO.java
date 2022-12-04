package com.epam.training.ticketservice.core.model;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder(setterPrefix = "with")
@Value
public class ScreeningDTO {

    private final MovieDTO movie;

    private final RoomDTO room;

    private final String time;

}
