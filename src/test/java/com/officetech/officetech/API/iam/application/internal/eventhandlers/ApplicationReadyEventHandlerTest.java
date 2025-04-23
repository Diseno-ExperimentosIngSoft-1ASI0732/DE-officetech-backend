package com.officetech.officetech.API.iam.application.internal.eventhandlers;

import com.officetech.officetech.API.Application;
import com.officetech.officetech.API.iam.domain.model.commands.SeedRolesCommand;
import com.officetech.officetech.API.iam.domain.services.RoleCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationReadyEventHandlerTest {

    private RoleCommandService roleCommandService;
    private ApplicationReadyEventHandler eventHandler;

    @BeforeEach
    void setUp() {
        roleCommandService = mock(RoleCommandService.class);
        eventHandler = new ApplicationReadyEventHandler(roleCommandService);
    }

    @Test
    void onApplicationReady_ShouldCallHandleWithSeedRolesCommand() {
        // Arrange
        ConfigurableApplicationContext applicationContext = mock(ConfigurableApplicationContext.class);
        when(applicationContext.getId()).thenReturn("mock-application");

        ApplicationReadyEvent event = new ApplicationReadyEvent(
                new SpringApplication(Application.class),
                new String[]{},
                applicationContext,
                Duration.ofMillis(100)
        );

        // Act
        eventHandler.on(event);

        // Assert
        ArgumentCaptor<SeedRolesCommand> captor = ArgumentCaptor.forClass(SeedRolesCommand.class);
        verify(roleCommandService, times(1)).handle(captor.capture());
        assertNotNull(captor.getValue());
    }

}
