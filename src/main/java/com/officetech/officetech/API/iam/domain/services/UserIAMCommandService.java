package com.officetech.officetech.API.iam.domain.services;

import com.officetech.officetech.API.iam.domain.model.aggregates.User;
import com.officetech.officetech.API.iam.domain.model.commands.SignInCommand;
import com.officetech.officetech.API.iam.domain.model.commands.SignUpCommand;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserIAMCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
