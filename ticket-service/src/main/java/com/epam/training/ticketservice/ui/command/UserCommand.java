package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.UserDTO;
import com.epam.training.ticketservice.core.services.service.UserService;
import com.epam.training.ticketservice.core.state.State;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class UserCommand {
    private final UserService userService;

    @ShellMethod(key="sign in privileged",value="Admin login")
    public String loginAsAdmin(String username, String password){
        State user = userService.loginAsAdmin(username, password);
        if(user==State.CORRECT){
            return "Signed in as "+userService.describe().get().getUsername();
        }else if(user==State.WRONG){
            return "Only admins can use this command";
        }else if(user==State.LOGGED_IN){
            return "You need to log out first";
        }
        return "Login failed due to incorrect credentials";
    }

    @ShellMethod(key="user login",value="User login")
    public String loginAsUser(String username, String password){
        State user=userService.loginAsUser(username, password);
        if(user==State.CORRECT){
            return "Signed in "+userService.describe().get().getUsername();
        }else if(user==State.LOGGED_IN){
            return "You need to log out first";
        }
        return "Invalid credentials";

    }

    @ShellMethod(key="sign out",value="Admin sign out")
    public String logoutAsAdmin(){
        State user = userService.logoutAsAdmin();
        if(user==State.CORRECT){
            return "You have singed out";
        }else if(user==State.WRONG){
            return "Only admins can use this command";
        }
        return "You are not logged in";
    }

    @ShellMethod(key = "user logout", value = "User logout")
    public String logoutAsUser() {
        Optional<UserDTO> user = userService.logoutAsUser();
        if (user.isEmpty()) {
            return "You need to login first!";
        }
        return user.get() + " is logged out!";
    }

    @ShellMethod(key="describe account", value="Admin describe account")
    public String describe(){
        Optional<UserDTO> user= userService.describe();
        if(user.isEmpty()){
            return "You are not signed in";
        }
        return "Signed in with privileged account "+user.get().getUsername();
    }


    @ShellMethod(key="user register",value="User registration")
    public String registerUser(String username, String password){
        try {
            Optional<UserDTO> user = userService.registerUser(username, password);
            if (user.isEmpty()) {
                return "Username is already in use";
            } else {
                return "Registration was successful!";
            }
        }catch(Exception e){
            return "Registration failed";
        }
    }


}
