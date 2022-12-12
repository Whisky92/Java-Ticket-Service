package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.model.MovieDto;
import com.epam.training.ticketservice.core.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;
    private final MovieEntity TEST_MOVIE = new MovieEntity("Titanic", "comedy", 120);

    @Test
    void testCreateMovieShouldReturnOptionalEmptyWhenMovieTitleDoesNotExist() {
        //Given
        Optional<MovieDto> expected = Optional.empty();
        Mockito.when(movieRepository.findByTitle(TEST_MOVIE.getTitle()))
                .thenReturn(Optional.empty());
        //When
        Optional<MovieDto> actual = movieService
                .createMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findByTitle(TEST_MOVIE.getTitle());
    }

    @Test
    void testCreateMovieShouldReturnOptionalMovieWhenMovieTitleExists() {
        //Given
        Optional<MovieDto> expected = Optional
                .of(new MovieDto(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength()));
        Mockito.when(movieRepository.findByTitle(TEST_MOVIE.getTitle()))
                .thenReturn(Optional.of(TEST_MOVIE));
        //When
        Optional<MovieDto> actual = movieService.createMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findByTitle(TEST_MOVIE.getTitle());
    }

    @Test
    void testCreateMovieShouldReturnOneWhenTheMovieWasUpdated() {
        //Given
        int expected = 1;
        Mockito.when(movieRepository.updateMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength()))
                .thenReturn(1);
        //When
        int actual = movieService.updateMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository)
                .updateMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength());
    }

    @Test
    void testCreateMovieShouldReturnZeroWhenTheMovieWasNotUpdated() {
        //Given
        int expected = 0;
        Mockito.when(movieRepository.updateMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength()))
                .thenReturn(0);
        //When
        int actual = movieService.updateMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository)
                .updateMovie(TEST_MOVIE.getTitle(), TEST_MOVIE.getGenre(), TEST_MOVIE.getLength());
    }

    @Test
    void testDeleteMovieShouldReturnOneWhenTheMovieWasDeleted() {
        //Given
        int expected = 1;
        Mockito.when(movieRepository.deleteByTitle(TEST_MOVIE.getTitle()))
                .thenReturn(1);
        //When
        int actual = movieService.deleteMovie(TEST_MOVIE.getTitle());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).deleteByTitle(TEST_MOVIE.getTitle());

    }

    @Test
    void testDeleteMovieShouldReturnZeroWhenNoMovieWasDeleted() {
        //Given
        int expected = 0;
        Mockito.when(movieRepository.deleteByTitle(TEST_MOVIE.getTitle()))
                .thenReturn(0);
        //When
        int actual = movieService.deleteMovie(TEST_MOVIE.getTitle());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).deleteByTitle(TEST_MOVIE.getTitle());

    }


}
