package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.ScreeningDTO;
import com.epam.training.ticketservice.core.services.ScreeningService;
import com.epam.training.ticketservice.core.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

    private final UserService userService;
    private final ScreeningService screeningService;

    @ShellMethodAvailability("isAdminAndLoggedIn")
    @ShellMethod(key = "create screening", value = "Create a screening")
    public String createScreening(String movieTitle, String roomName, String startTime) {
        return screeningService.createScreening(movieTitle, roomName, startTime);
    }


    @ShellMethod(key = "list screenings", value = "List screenings")
    public String getScreeningList() {
        List<ScreeningDTO> screenings = screeningService.getScreeningList();
        if (screenings.isEmpty()) {
            return "There are no screenings";
        }
        String response = "";
        for (ScreeningDTO screeningDTO : screenings) {
            response += screeningDTO.getMovie().getTitle() + " ("
                    + screeningDTO.getMovie().getGenre() + ", "
                    + screeningDTO.getMovie().getLength()
                    + " minutes), screened in room "
                    + screeningDTO.getRoom().getName() + ", at "
                    + screeningDTO.getTime() + "\n";
        }
        response = response.substring(0, response.length()-1);
        return response;
    }
    @ShellMethodAvailability("isAdminAndLoggedIn")
    @ShellMethod(key = "delete screening", value = "Delete a screening")
    public String deleteScreening(String movieTitle, String roomName, String startTime) {
        boolean rowDeleted = (screeningService.deleteScreening(movieTitle, roomName, startTime) == 1);
        if (rowDeleted) {
            return "A row was deleted";
        }
        return "No rows were deleted";
    }

    private Availability isAdminAndLoggedIn() {
        return userService.describe().isPresent() && userService.describe().get().getRole() == UserEntity.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not logged in as an admin!");
    }
}
