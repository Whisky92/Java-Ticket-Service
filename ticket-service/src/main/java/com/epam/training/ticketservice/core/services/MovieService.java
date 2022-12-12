package com.epam.training.ticketservice.core.services;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.model.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieDto> createMovie(String title, String genre, int length);

    int updateMovie(String title, String genre, int length);

    MovieDto convertEntityToDto(MovieEntity movieEntity);

    int deleteMovie(String title);

    List<MovieDto> getMovieList();
}
