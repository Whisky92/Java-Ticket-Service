package com.epam.training.ticketservice.core.services;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieDTO> createMovie(String title, String genre, int length);

    int updateMovie(String title, String genre, int length);

    public MovieDTO convertEntityToDTO(MovieEntity movieEntity);

    int deleteMovie(String title);

    List<MovieDTO> getMovieList();
}
