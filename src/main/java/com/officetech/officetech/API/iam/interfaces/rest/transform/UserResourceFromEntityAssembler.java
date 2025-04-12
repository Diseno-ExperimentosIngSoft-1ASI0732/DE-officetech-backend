package com.officetech.officetech.API.iam.interfaces.rest.transform;

import com.officetech.officetech.API.iam.domain.model.aggregates.User;
import com.officetech.officetech.API.iam.domain.model.entities.Role;
import com.officetech.officetech.API.iam.interfaces.rest.resources.UserIAMResource;
import com.officetech.officetech.API.services.interfaces.rest.resources.UserResource;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

public class UserResourceFromEntityAssembler {
    public static UserIAMResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserIAMResource(user.getId(), user.getUsername(), roles);
    }
}

