package com.officetech.officetech.API.profile.internal.commandservices;


import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.profile.domain.services.ProfileCommandService;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final UserAuthRepository userAuthRepository;

    public ProfileCommandServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }


    @Override
    public Optional<UserAuth> handle(UpdateProfileCommand command) {
        var user = userAuthRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setPassword(command.password());
        user.setPhone(command.phone());
        var profile = userAuthRepository.save(user);
        return Optional.of(profile);
    }
}