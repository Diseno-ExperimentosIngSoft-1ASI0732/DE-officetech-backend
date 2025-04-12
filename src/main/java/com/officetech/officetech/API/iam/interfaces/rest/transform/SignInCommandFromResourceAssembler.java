package com.officetech.officetech.API.iam.interfaces.rest.transform;

import com.officetech.officetech.API.iam.domain.model.commands.SignInCommand;
import com.officetech.officetech.API.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
