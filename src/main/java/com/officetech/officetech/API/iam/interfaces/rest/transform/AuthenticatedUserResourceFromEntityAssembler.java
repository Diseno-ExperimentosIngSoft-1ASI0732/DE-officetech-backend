package com.officetech.officetech.API.iam.interfaces.rest.transform;

import com.officetech.officetech.API.iam.domain.model.aggregates.User;
import com.officetech.officetech.API.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}

