package com.officetech.officetech.API.profile.internal.commandservices;

import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProfileCommandServiceImplTest {

    private UserAuthRepository userAuthRepository;
    private ProfileCommandServiceImpl profileCommandService;

    @BeforeEach
    void setup() {
        userAuthRepository = mock(UserAuthRepository.class);
        profileCommandService = new ProfileCommandServiceImpl(userAuthRepository);
    }

    @Test
    void handle_WhenUserExists_UpdatesUserProfile() {
        // Arrange
        var userId = 1L;
        var user = new UserAuth();
        user.setId(userId);
        user.setFirstName("Old");
        user.setLastName("Name");

        var command = new UpdateProfileCommand(userId, "New", "Name", "password123", "999999999");

        when(userAuthRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userAuthRepository.save(any(UserAuth.class))).thenReturn(user);

        // Act
        var result = profileCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("New", result.get().getFirstName());
        assertEquals("password123", result.get().getPassword());

        verify(userAuthRepository).findById(userId);
        verify(userAuthRepository).save(user);
    }

    @Test
    void handle_WhenUserNotFound_ThrowsException() {
        // Arrange
        var userId = 2L;
        var command = new UpdateProfileCommand(userId, "Test", "User", "pass", "123");

        when(userAuthRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> profileCommandService.handle(command));
        verify(userAuthRepository).findById(userId);
    }
}
