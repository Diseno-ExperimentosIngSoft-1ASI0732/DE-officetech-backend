package com.officetech.officetech.API.usersauth.application.internal.commandservices;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.commands.CreateUserAuthCommand;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthCommandService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthCommandServiceImpl implements UserAuthCommandService {
    private final UserAuthRepository userAuthRepository;

    public UserAuthCommandServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public Optional<UserAuth> handle(CreateUserAuthCommand command) {
        System.out.println("UserAuthCommandServiceImpl: Handling create user command");
        var userAuth = new UserAuth(command);
        System.out.println("UserAuthCommandServiceImpl: Saving user entity");
        var createdUserAuth = userAuthRepository.save(userAuth);
        System.out.println("UserAuthCommandServiceImpl - Repository: User entity saved");
        return Optional.of(createdUserAuth);
    }
}
