package com.officetech.officetech.API.profile.domain.services;

import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

import java.util.Optional;


public interface ProfileCommandService {
    Optional<UserAuth> handle(UpdateProfileCommand command);
}