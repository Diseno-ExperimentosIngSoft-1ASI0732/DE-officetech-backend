package com.officetech.officetech.API.usersauth.interfaces.rest.transform;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.UserAuthResource;

/*
* UserAuthResourceFromEntityAssembler is an assembler for converting UserAuth objects to UserAuthResource objects.
* <p>This assembler provides a method to convert a UserAuth object to its corresponding UserAuthResource object,
* which is used for API responses.</p>
* */
public class UserAuthResourceFromEntityAssembler {

    public static UserAuthResource toResourceFromEntity(UserAuth entity) {
        System.out.println("UserAuthResourceFromEntityAssembler: converting entity to resource");
        return new UserAuthResource(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.getRole());
    }
}
