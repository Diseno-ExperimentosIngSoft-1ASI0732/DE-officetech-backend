package com.officetech.officetech.API.usersauth.domain.services;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserAuthQueryService {
    Optional<UserAuth> handle(GetUserByEmailQuery query);
    boolean handle(AuthUserQuery query);
    Optional<UserAuth> handle(GetUserByIdQuery email);
}
