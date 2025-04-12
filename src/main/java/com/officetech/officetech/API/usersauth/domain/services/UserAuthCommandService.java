package com.officetech.officetech.API.usersauth.domain.services;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.commands.CreateUserAuthCommand;

import java.util.Optional;

public interface UserAuthCommandService {
    Optional<UserAuth> handle(CreateUserAuthCommand command);
}
