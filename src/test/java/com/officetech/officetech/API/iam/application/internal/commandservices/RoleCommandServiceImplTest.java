package com.officetech.officetech.API.iam.application.internal.commandservices;

import com.officetech.officetech.API.iam.domain.model.commands.SeedRolesCommand;
import com.officetech.officetech.API.iam.domain.model.entities.Role;
import com.officetech.officetech.API.iam.domain.model.valueobjects.Roles;
import com.officetech.officetech.API.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RoleCommandServiceImplTest {

    private RoleRepository roleRepository;
    private RoleCommandServiceImpl roleCommandService;

    @BeforeEach
    void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleCommandService = new RoleCommandServiceImpl(roleRepository);
    }

    @Test
    void handle_ShouldSaveAllNonExistingRoles() {
        // Arrange
        for (Roles role : Roles.values()) {
            when(roleRepository.existsByName(role)).thenReturn(false);
        }

        // Act
        roleCommandService.handle(new SeedRolesCommand());

        // Assert
        for (Roles role : Roles.values()) {
            verify(roleRepository).existsByName(role);
            verify(roleRepository).save(argThat(r -> r.getName().equals(role)));
        }
    }

    @Test
    void handle_ShouldNotSaveRolesThatAlreadyExist() {
        // Arrange
        for (Roles role : Roles.values()) {
            when(roleRepository.existsByName(role)).thenReturn(true);
        }

        // Act
        roleCommandService.handle(new SeedRolesCommand());

        // Assert
        for (Roles role : Roles.values()) {
            verify(roleRepository).existsByName(role);
            verify(roleRepository, never()).save(any(Role.class));
        }
    }
}
