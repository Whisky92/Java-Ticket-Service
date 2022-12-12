package com.epam.training.ticketservice.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@Value
public class RoomDto {

    private final String name;

    private final int rowCount;

    private final int columnCount;

}
