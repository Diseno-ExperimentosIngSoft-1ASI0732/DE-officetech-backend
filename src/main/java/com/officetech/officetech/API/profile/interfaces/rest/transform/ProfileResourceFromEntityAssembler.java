package com.officetech.officetech.API.profile.interfaces.rest.transform;

import com.officetech.officetech.API.profile.interfaces.rest.resources.ProfileResource;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(UserAuth entity) {
        return new ProfileResource(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getPhone(), entity.getPassword());
    }
}
