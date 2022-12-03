package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.services.service.RoomService;
import com.epam.training.ticketservice.core.services.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;


@ShellComponent
@AllArgsConstructor
public class RoomCommand {

    private final RoomService roomService;

    private final UserService userService;
    @ShellMethodAvailability("isAdminAndLoggedIn")
    @ShellMethod(key="create room", value="Create a new room")
    public String createRoom(String name,int rowCount, int columnCount){
        try {
            Optional<RoomDTO> room = roomService.createRoom(name, rowCount, columnCount);
            if (room.isEmpty()) {
                return "A new room successfully created with name "+name;
            } else {
                return "A room with this name already exits";
            }
        }catch(Exception e){
            return "Registration failed";
        }
    }
    @ShellMethodAvailability("isAdminAndLoggedIn")
    @ShellMethod(key="update room",value="Update a room")
    public String updateRoom(String name, int rowCount, int columnCount){
        boolean rowChanged = (roomService.updateRoom(name, rowCount, columnCount)==1);
        if(rowChanged){
            return "A row was updated";
        }
        return "No rows were updated";
    }
    @ShellMethodAvailability("isAdminAndLoggedIn")
    @ShellMethod(key="delete room",value = "Delete a room")
    public String deleteRoom(String name){
        boolean rowDeleted=(roomService.deleteRoom(name)==1);
        if(rowDeleted){
            return "A row was deleted";
        }
        return "No rows were deleted";
    }

    @ShellMethod(key="list rooms",value="List all rooms")
    public String getRoomList(){
        List<RoomDTO> rooms = roomService.getRoomList();
        if(rooms.isEmpty()){
            return "There are no rooms at the moment";
        }
        String response="";
        for(RoomDTO roomDTO : rooms){
            response +="Room "+roomDTO.getName()+
                    " with "+roomDTO.getRowCount()*roomDTO.getColumnCount() + " seats, "+
                    roomDTO.getRowCount()+" rows and  "+roomDTO.getColumnCount()
                    +" columns \n";
        }
        return response;
    }


    private Availability isAdminAndLoggedIn() {
        return userService.describe().isPresent() && userService.describe().get().getRole()== UserEntity.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not logged in as an admin!");
    }
}
