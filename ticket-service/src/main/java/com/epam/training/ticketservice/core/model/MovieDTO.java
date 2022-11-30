package com.epam.training.ticketservice.core.model;

import lombok.Value;

@Value
public class MovieDTO {
    private final String title;
    private final String genre;
    private final int length;
}
