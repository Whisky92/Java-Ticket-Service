package com.epam.training.ticketservice.core.model;

import com.epam.training.ticketservice.core.entity.UserEntity;
import lombok.Value;

@Value
public class UserDto {

    private final String username;

    private final UserEntity.Role role;


}
