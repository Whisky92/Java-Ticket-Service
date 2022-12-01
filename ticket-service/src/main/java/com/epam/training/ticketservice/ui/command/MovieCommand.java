package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.UserDTO;
import com.epam.training.ticketservice.core.repository.MovieRepository;
import com.epam.training.ticketservice.core.services.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
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

    @ShellMethod(key="update movie",value="Update a movie")
    public String updateMovie(String title, String genre, int length){
        boolean rowChanged = (movieService.updateMovie(title, genre, length)==1);
        if(rowChanged){
            return "A row was updated";
        }
        return "No rows were updated";
    }

    @ShellMethod(key="delete movie",value = "Delete a movie")
    public String deleteMovie(String title){
        boolean rowDeleted=(movieService.deleteMovie(title)==1);
        if(rowDeleted){
            return "A row was deleted";
        }
        return "No rows were deleted";
    }

    @ShellMethod(key="list movies",value="List all movies")
    public String getMovieList(){
        List<MovieDTO> movies = movieService.getMovieList();
        if(movies.isEmpty()){
            return "There are no movies at the moment";
        }
        String response="";
        for(MovieDTO movieDTO : movies){
            response+=movieDTO.getTitle()+
                    " ("+movieDTO.getGenre()
                    +", "+movieDTO.getLength()
                    +")\n";
        }
        return response;
    }


}