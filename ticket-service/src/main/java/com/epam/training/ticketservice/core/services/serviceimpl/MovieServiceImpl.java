package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.repository.MovieRepository;
import com.epam.training.ticketservice.core.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public int updateMovie(String title, String genre, int length){
        return movieRepository.updateMovie(title, genre, length);
    }

    @Override
    public int deleteMovie(String title){
        return movieRepository.deleteByTitle(title);
    }

    @Override
    public List<MovieDTO> getMovieList(){
        return movieRepository.findAll().stream()
           .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private MovieEntity createEntity(String title, String genre, int length){
        return new MovieEntity(title,genre,length);
    }

    public MovieDTO convertEntityToDTO(MovieEntity movieEntity){
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
