package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.UserDto;
import com.epam.training.ticketservice.core.services.UserService;
import com.epam.training.ticketservice.core.state.State;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class UserCommand {
    private final UserService userService;

    @ShellMethod(key = "sign in privileged", value = "Admin login")
    public String loginAsAdmin(String username, String password) {

        State user = userService.loginAsAdmin(username, password);

        if (user == State.CORRECT) {
            return "Signed in as " + userService.describe().get().getUsername();
        } else if (user == State.LOGGED_IN) {
            return "You need to log out first";
        }
        return "Login failed due to incorrect credentials";
    }

    @ShellMethodAvailability("isAdminAndLoggedIn")
    @ShellMethod(key = "sign out", value = "Admin sign out")
    public String logoutAsAdmin() {

        State user = userService.logout();

        if (user == State.CORRECT) {
            return "You have singed out";
        } else if (user == State.WRONG) {
            return "Only admins can use this command";
        }
        return "You are not logged in";
    }

    @ShellMethod(key = "describe account", value = "Admin describe account")
    public String describe() {
        Optional<UserDto> user = userService.describe();
        if (user.isEmpty()) {
            return "You are not signed in";
        }
        return "Signed in with privileged account '" + user.get().getUsername() + "'";
    }


    private Availability isAdminAndLoggedIn() {
        return userService.describe().isPresent() && userService.describe().get().getRole() == UserEntity.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not logged in as an admin!");
    }


}
