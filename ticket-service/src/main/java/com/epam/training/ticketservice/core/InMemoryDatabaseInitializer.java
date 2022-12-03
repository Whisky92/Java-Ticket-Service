package com.epam.training.ticketservice.core;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InMemoryDatabaseInitializer {
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        UserEntity admin = new UserEntity("admin","admin",UserEntity.Role.ADMIN);
        userRepository.save(admin);
    }


}
