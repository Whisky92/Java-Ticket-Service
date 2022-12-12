package com.epam.training.ticketservice.core.model;

import lombok.Builder;
import lombok.Value;

@Builder(setterPrefix = "with")
@Value
public class ScreeningDto {

    private final MovieDto movie;

    private final RoomDto room;

    private final String time;

}
