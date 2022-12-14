package com.epam.training.ticketservice.core.services;

import com.epam.training.ticketservice.core.model.UserDto;
import com.epam.training.ticketservice.core.state.State;

import java.util.Optional;


public interface UserService {

    State loginAsAdmin(String username, String password);

    State logout();

    Optional<UserDto> describe();
}