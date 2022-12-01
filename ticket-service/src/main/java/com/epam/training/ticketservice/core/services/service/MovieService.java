package com.epam.training.ticketservice.core.services.service;

import com.epam.training.ticketservice.core.model.MovieDTO;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieDTO> createMovie(String title, String genre, int length);
    int updateMovie(String title, String genre, int length);

    int deleteMovie(String title);

    List<MovieDTO> getMovieList();
}
