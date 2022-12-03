package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.UserEntity;
import com.epam.training.ticketservice.core.model.UserDTO;
import com.epam.training.ticketservice.core.repository.UserRepository;
import com.epam.training.ticketservice.core.state.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private final UserEntity TEST_ADMIN = new UserEntity("admin", "admin", UserEntity.Role.ADMIN);

    @Test
    void testLoginAsAdminShouldReturnCorrectStateWhenUsernameAndPasswordAreCorrect(){
        //Given
        State expected = State.CORRECT;
        Mockito.when(userRepository.findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword()))
                .thenReturn(Optional.of(TEST_ADMIN));
        //When
        State actual = userService.loginAsAdmin(TEST_ADMIN.getUsername(),TEST_ADMIN.getPassword());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword());
    }

    @Test
    void testLoginAsAdminShouldReturnLoggedInStateWhenUserIsAlreadyLoggedIn(){
        //Given
        State expected = State.LOGGED_IN;
        Mockito.when(userRepository.findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword()))
                .thenReturn(Optional.of(TEST_ADMIN));
        userService.loginAsAdmin(TEST_ADMIN.getUsername(),TEST_ADMIN.getPassword());
        //When
        State actual = userService.loginAsAdmin(TEST_ADMIN.getUsername(),TEST_ADMIN.getPassword());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword());
    }
    @Test
    void testLoginAsAdminShouldReturnNotFoundStateWhenUsernameOrPasswordIsIncorrect(){
        //Given
        State expected = State.NOT_FOUND;
        Mockito.when(userRepository.findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword()))
                .thenReturn(Optional.empty());
        //When
        State actual = userService.loginAsAdmin(TEST_ADMIN.getUsername(),TEST_ADMIN.getPassword());
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword());
    }

    @Test
    void testLogoutShouldReturnCorrectStateWhenUserIsLoggedIn(){
        //Given
        State expected = State.CORRECT;
        Mockito.when(userRepository.findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword()))
                .thenReturn(Optional.of(TEST_ADMIN));
        userService.loginAsAdmin(TEST_ADMIN.getUsername(),TEST_ADMIN.getPassword());
        //When
        State actual = userService.logout();
        //Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword());
    }

    @Test
    void testLogoutShouldReturnLoggedInStateWhenUserIsNotLoggedIn(){
        //Given
        State expected = State.LOGGED_IN;
        //When
        State actual = userService.logout();
        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDescribeShouldReturnCurrentOptionalUserWhenSignedInPrivileged() {
        // Given
        Optional<UserDTO> expected =Optional.of(new UserDTO(TEST_ADMIN.getUsername(),TEST_ADMIN.getRole()));
        Mockito.when(userRepository.findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword()))
                .thenReturn(Optional.of(TEST_ADMIN));
        userService.loginAsAdmin(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword());

        // When
        Optional<UserDTO> actual = userService.describe();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userRepository).findByUsernameAndPassword(TEST_ADMIN.getUsername(), TEST_ADMIN.getPassword());
    }



}
