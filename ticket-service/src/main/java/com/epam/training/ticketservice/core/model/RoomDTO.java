package com.epam.training.ticketservice.core.model;

import lombok.Builder;
import lombok.Value;

@Builder(setterPrefix = "with")
@Value
public class RoomDTO{
    private final String name;
    private final int rowCount;
    private final int columnCount;
}
