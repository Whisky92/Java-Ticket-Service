package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.UserDTO;
import com.epam.training.ticketservice.core.services.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class MovieCommand {

    private final MovieService movieService;

    @ShellMethod(key="create movie", value="Create a new movie")
    public String createMovie(String title,String genre, int length){
        try {
            Optional<MovieDTO> movie = movieService.createMovie(title, genre, length);
            if (movie.isEmpty()) {
                return "A new movie successfully created with "+title;
            } else {
                return "A movie with this name already exits";
            }
        }catch(Exception e){
            return "Registration failed";
        }
    }


}