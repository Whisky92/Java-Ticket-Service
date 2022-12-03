package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.model.ScreeningDTO;
import com.epam.training.ticketservice.core.services.service.ScreeningService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Date;
import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

    private final ScreeningService screeningService;
    @ShellMethod(key="create screening",value="Create a screening")
    public String createScreening(String movieTitle, String roomName, String startTime){
        return screeningService.createScreening(movieTitle, roomName, startTime);
    }

    @ShellMethod(key="list screenings", value="List screenings")
    public String getScreeningList(){
        List<ScreeningDTO> screenings = screeningService.getScreeningList();
        if(screenings.isEmpty()){
            return "There are no screenings";
        }
        String response="";
        for(ScreeningDTO screeningDTO : screenings){
            response += screeningDTO.getMovie().getTitle()+"("
                    +screeningDTO.getMovie().getGenre()+", "
                    +screeningDTO.getMovie().getLength()
                    +" minutes), screened in room "
                    + screeningDTO.getRoom().getName()+", at "
                    + screeningDTO.getTime();
        }
        return response;
    }
}
