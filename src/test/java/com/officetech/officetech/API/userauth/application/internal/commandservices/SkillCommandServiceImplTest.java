package com.officetech.officetech.API.userauth.application.internal.commandservices;

import com.officetech.officetech.API.usersauth.application.internal.commandservices.SkillCommandServiceImpl;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.commands.CreateSkillCommand;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.SkillRepository;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillCommandServiceImplTest {

    private UserAuthRepository userAuthRepository;
    private SkillRepository skillRepository;
    private SkillCommandServiceImpl service;

    @BeforeEach
    void setUp() {
        userAuthRepository = mock(UserAuthRepository.class);
        skillRepository = mock(SkillRepository.class);
        service = new SkillCommandServiceImpl(userAuthRepository, skillRepository);
    }

    @Test
    void handleCreateSkill_ShouldSaveSkill_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        CreateSkillCommand command = new CreateSkillCommand(userId, "Java");
        UserAuth user = mock(UserAuth.class);
        when(userAuthRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<Skill> result = service.handle(command);

        // Assert
        assertTrue(result.isPresent());
        verify(skillRepository).save(any(Skill.class));
    }

    @Test
    void handleCreateSkill_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        CreateSkillCommand command = new CreateSkillCommand(userId, "Java");
        when(userAuthRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.handle(command));
    }

    @Test
    void handleDeleteSkill_ShouldDeleteSkill_WhenSkillExists() {
        // Arrange
        Long skillId = 1L;
        Skill skill = mock(Skill.class);
        when(skillRepository.findById(skillId)).thenReturn(Optional.of(skill));

        // Act
        Boolean result = service.handle(skillId);

        // Assert
        assertTrue(result);
        verify(skillRepository).delete(skill);
    }

    @Test
    void handleDeleteSkill_ShouldThrowException_WhenSkillNotFound() {
        // Arrange
        Long skillId = 1L;
        when(skillRepository.findById(skillId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.handle(skillId));
    }
}
