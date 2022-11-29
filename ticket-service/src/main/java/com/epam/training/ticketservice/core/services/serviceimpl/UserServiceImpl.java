package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.UserDTO;
import com.epam.training.ticketservice.core.repository.UserRepository;
import com.epam.training.ticketservice.core.services.service.UserService;
import com.epam.training.ticketservice.core.state.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private UserDTO loggedInUser = null;
    @Override
    public State loginAsAdmin(String username, String password){
        if(describe().isPresent()){
            return State.LOGGED_IN;
        }
        Optional<UserDTO> user = login(username, password);
        if(user.isEmpty()){
            return State.NOT_FOUND;
        }
        if(user.get().getRole()==UserEntity.Role.USER){
            return State.WRONG;
        }
        loggedInUser=user.get();
        return State.CORRECT;
    }

    @Override
    public State loginAsUser(String username, String password){
        if(describe().isPresent()){
            return State.LOGGED_IN;
        }
        Optional<UserDTO> user = login(username, password);
        if(user.isPresent()){
            loggedInUser=user.get();
            return State.CORRECT;
        }
        return State.NOT_FOUND;

    }

    @Override
    public State logoutAsAdmin(){
        if(describe().isEmpty()){
            return State.LOGGED_IN;
        }
        UserDTO user = describe().get();
        if(user.getRole()== UserEntity.Role.ADMIN){
            loggedInUser=null;
            return State.CORRECT;
        }
        return State.WRONG;
    }

    @Override
    public Optional<UserDTO> logoutAsUser(){
        Optional<UserDTO> previouslyLoggedInUser = describe();
        loggedInUser = null;
        return previouslyLoggedInUser;
    }
    private Optional<UserDTO> login(String username, String password){
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(username, password);
        if(user.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(new UserDTO(user.get().getUsername(),user.get().getRole()));
        }
    }

    @Override
    public Optional<UserDTO> registerUser(String username, String password){
        Optional<UserEntity> userToRegister = userRepository.findByUsername(username);
        if(userToRegister.isPresent())
        {
            return Optional.empty();
        }else{
            UserEntity user = new UserEntity(username, password, UserEntity.Role.USER);
            userRepository.save(user);
            Optional<UserDTO> userDTO = Optional.of(new UserDTO(username, UserEntity.Role.USER));
            return userDTO;
        }

    }

    @Override
    public Optional<UserDTO> describe(){
        return Optional.ofNullable(loggedInUser);
    }
}
