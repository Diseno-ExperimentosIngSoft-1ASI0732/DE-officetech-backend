package com.officetech.officetech.API.iam.application.internal.commandservices;

import com.officetech.officetech.API.iam.application.internal.outboundservices.hashing.HashingService;
import com.officetech.officetech.API.iam.application.internal.outboundservices.tokens.TokenService;
import com.officetech.officetech.API.iam.domain.model.aggregates.User;
import com.officetech.officetech.API.iam.domain.model.commands.SignInCommand;
import com.officetech.officetech.API.iam.domain.model.commands.SignUpCommand;
import com.officetech.officetech.API.iam.domain.model.entities.Role;
import com.officetech.officetech.API.iam.domain.model.valueobjects.Roles;
import com.officetech.officetech.API.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.officetech.officetech.API.iam.infrastructure.persistence.jpa.repositories.UserIAMRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserIAMCommandServiceImplTest {

    private UserIAMRepository userRepository;
    private HashingService hashingService;
    private TokenService tokenService;
    private RoleRepository roleRepository;
    private UserIAMCommandServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserIAMRepository.class);
        hashingService = mock(HashingService.class);
        tokenService = mock(TokenService.class);
        roleRepository = mock(RoleRepository.class);
        userService = new UserIAMCommandServiceImpl(userRepository, hashingService, tokenService, roleRepository);
    }

    @Test
    void handleSignUp_ShouldCreateUser_WhenUsernameNotExists() {
        // Arrange
        SignUpCommand command = new SignUpCommand("user1", "password123", List.of("ROLE_TECHNICIAN"));

        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_TECHNICIAN)).thenReturn(Optional.of(new Role(Roles.ROLE_TECHNICIAN)));
        when(hashingService.encode("password123")).thenReturn("encodedPassword");

        User savedUser = new User("user1", "encodedPassword", List.of(new Role(Roles.ROLE_TECHNICIAN)));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(savedUser));

        // Act
        Optional<User> result = userService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void handleSignUp_ShouldThrowException_WhenUsernameExists() {
        // Arrange
        when(userRepository.existsByUsername("user1")).thenReturn(true);
        SignUpCommand command = new SignUpCommand("user1", "password", List.of());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.handle(command));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void handleSignIn_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        User user = new User("user1", "encodedPass", List.of(new Role(Roles.ROLE_TECHNICIAN)));
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));
        when(hashingService.matches("password123", "encodedPass")).thenReturn(true);
        when(tokenService.generateToken("user1")).thenReturn("mocked-token");

        SignInCommand command = new SignInCommand("user1", "password123");

        // Act
        Optional<ImmutablePair<User, String>> result = userService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("mocked-token", result.get().getRight());
        verify(tokenService).generateToken("user1");
    }

    @Test
    void handleSignIn_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findByUsername("user1")).thenReturn(Optional.empty());

        SignInCommand command = new SignInCommand("user1", "pass");

        assertThrows(RuntimeException.class, () -> userService.handle(command));
    }

    @Test
    void handleSignIn_ShouldThrowException_WhenPasswordIsInvalid() {
        User user = new User("user1", "encodedPass", List.of());
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));
        when(hashingService.matches("wrongpass", "encodedPass")).thenReturn(false);

        SignInCommand command = new SignInCommand("user1", "wrongpass");

        assertThrows(RuntimeException.class, () -> userService.handle(command));
    }
}
