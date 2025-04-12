package com.officetech.officetech.API.iam.interfaces.rest.transform;

import com.officetech.officetech.API.iam.domain.model.commands.SignUpCommand;
import com.officetech.officetech.API.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.username(), resource.password(), resource.roles());
    }

}

