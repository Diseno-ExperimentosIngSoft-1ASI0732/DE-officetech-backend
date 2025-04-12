package com.officetech.officetech.API.iam.application.internal.commandservices;

import com.officetech.officetech.API.iam.application.internal.outboundservices.hashing.HashingService;
import com.officetech.officetech.API.iam.application.internal.outboundservices.tokens.TokenService;
import com.officetech.officetech.API.iam.domain.model.aggregates.User;
import com.officetech.officetech.API.iam.domain.model.commands.SignInCommand;
import com.officetech.officetech.API.iam.domain.model.commands.SignUpCommand;
import com.officetech.officetech.API.iam.domain.model.entities.Role;
import com.officetech.officetech.API.iam.domain.model.valueobjects.Roles;
import com.officetech.officetech.API.iam.domain.services.UserIAMCommandService;
import com.officetech.officetech.API.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.officetech.officetech.API.iam.infrastructure.persistence.jpa.repositories.UserIAMRepository;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserIAMCommandServiceImpl implements UserIAMCommandService {
    private final UserIAMRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserIAMCommandServiceImpl(UserIAMRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var stringRoles = command.roles();
        var roles = new ArrayList<Role>();
        if (stringRoles == null || stringRoles.isEmpty()) {
            var storedRole = roleRepository.findByName(Roles.ROLE_TECHNICIAN);
            storedRole.ifPresent(roles::add);
        } else {
            stringRoles.forEach(role -> {
                var storedRole = roleRepository.findByName(Roles.valueOf(role));
                storedRole.ifPresent(roles::add);
            });
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}