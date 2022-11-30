package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.repository.MovieRepository;
import com.epam.training.ticketservice.core.services.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Optional<MovieDTO> createMovie(String title, String genre, int length){
        Optional<MovieDTO> movieDTO = convertEntityToDTO(movieRepository.findByTitle(title));
        if(movieDTO.isEmpty()){
            movieRepository.save(createEntity(title, genre, length));
        }
        return movieDTO;
    }

    private MovieEntity createEntity(String title, String genre, int length){
        return new MovieEntity(title,genre,length);
    }

    private MovieDTO convertEntityToDTO(MovieEntity movieEntity){
        return MovieDTO.builder()
                .withTitle(movieEntity.getTitle())
                .withGenre(movieEntity.getGenre())
                .withLength(movieEntity.getLength())
                .build();
    }

    private Optional<MovieDTO> convertEntityToDTO(Optional<MovieEntity> movieEntity){
        return movieEntity.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDTO(movieEntity.get()));
    }



}
