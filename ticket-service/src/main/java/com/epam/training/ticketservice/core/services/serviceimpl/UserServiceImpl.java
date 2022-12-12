package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.UserDto;
import com.epam.training.ticketservice.core.repository.UserRepository;
import com.epam.training.ticketservice.core.services.UserService;
import com.epam.training.ticketservice.core.state.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserDto loggedInUser = null;

    @Override
    public State loginAsAdmin(String username, String password) {
        if (describe().isPresent()) {
            return State.LOGGED_IN;
        }
        Optional<UserDto> user = login(username, password);
        if (user.isEmpty()) {
            return State.NOT_FOUND;
        }
        loggedInUser = user.get();
        return State.CORRECT;
    }

    @Override
    public State logout() {
        if (describe().isEmpty()) {
            return State.LOGGED_IN;
        }
        loggedInUser = null;
        return State.CORRECT;
    }


    private Optional<UserDto> login(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new UserDto(user.get().getUsername(), user.get().getRole()));
        }
    }

    @Override
    public Optional<UserDto> describe() {
        return Optional.ofNullable(loggedInUser);
    }
}
