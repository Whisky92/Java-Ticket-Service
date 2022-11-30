package com.epam.training.ticketservice.core.services.service;

import com.epam.training.ticketservice.core.model.MovieDTO;

import java.util.Optional;

public interface MovieService {

    Optional<MovieDTO> createMovie(String title, String genre, int length);
}
