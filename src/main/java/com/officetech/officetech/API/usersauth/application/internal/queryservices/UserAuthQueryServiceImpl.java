package com.officetech.officetech.API.usersauth.application.internal.queryservices;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetAllSkillsQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByIdQuery;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthQueryService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class UserAuthQueryServiceImpl implements UserAuthQueryService {
    UserAuthRepository userAuthRepository;
    public UserAuthQueryServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public Optional<UserAuth> handle(GetUserByEmailQuery query) {
        try {
            return userAuthRepository.findUserAuthByEmail(query.email());
        }catch(Exception e) {
            System.out.println("UserAuthCommandServiceImpl: Error while saving user entity" + e);
            return Optional.empty();
        }

    }

    @Override
    public boolean handle(AuthUserQuery query) {
        try {
            // manage transactions with repository
            List<UserAuth> users = userAuthRepository.findAll();
            return users.stream().anyMatch(user -> user.getEmail().equals(query.email().getEmail()) && user.getPassword().equals(query.password().getPassword()));
        }catch(Exception e) {
            System.out.println("UserAuthCommandServiceImpl: Error while saving user entity" + e);
            return false;
        }
    }

    @Override
    public Optional<UserAuth> handle(GetUserByIdQuery email) {
        try {
            return userAuthRepository.findById(email.id());
        }catch(Exception e) {
            System.out.println("UserAuthCommandServiceImpl: Error while saving user entity" + e);
            return Optional.empty();
        }
    }


}
