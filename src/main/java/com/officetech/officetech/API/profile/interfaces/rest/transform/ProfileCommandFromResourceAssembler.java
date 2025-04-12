package com.officetech.officetech.API.profile.interfaces.rest.transform;

import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.profile.interfaces.rest.resources.ProfileResource;
import org.springframework.stereotype.Component;

@Component
public class ProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(ProfileResource resource) {
        System.out.println("ProfileCommandFromResourceAssembler: converting resource to command");
        return new UpdateProfileCommand(resource.id(), resource.firstName(), resource.lastName(), resource.phone(), resource.password());
    }
}

