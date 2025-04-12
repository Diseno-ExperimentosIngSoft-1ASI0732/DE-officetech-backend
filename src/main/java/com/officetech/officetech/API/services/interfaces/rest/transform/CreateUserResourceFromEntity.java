package com.officetech.officetech.API.services.interfaces.rest.transform;

import com.officetech.officetech.API.services.interfaces.rest.resources.UserResource;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

public class CreateUserResourceFromEntity {
    public static UserResource toResourceFromEntity(UserAuth entity) {
        return new UserResource(entity.getId(), entity.getRole(), entity.getFirstName() + " " + entity.getLastName(), entity.getEmail());
    }
}
