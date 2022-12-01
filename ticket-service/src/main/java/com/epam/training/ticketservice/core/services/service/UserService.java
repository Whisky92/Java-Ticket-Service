package com.epam.training.ticketservice.core.services.service;

import com.epam.training.ticketservice.core.model.UserDTO;
import com.epam.training.ticketservice.core.services.serviceimpl.UserServiceImpl;
import com.epam.training.ticketservice.core.state.State;
import org.apache.catalina.User;

import java.util.Optional;


public interface UserService {
    State loginAsAdmin(String username, String password);

    State loginAsUser(String username, String password);

    State logoutAsAdmin();

    Optional<UserDTO> registerUser(String username, String password);
    Optional<UserDTO> logoutAsUser();

    Optional<UserDTO> describe();
}