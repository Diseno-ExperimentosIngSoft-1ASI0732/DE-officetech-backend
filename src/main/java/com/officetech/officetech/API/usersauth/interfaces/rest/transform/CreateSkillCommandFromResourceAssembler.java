package com.officetech.officetech.API.usersauth.interfaces.rest.transform;

import com.officetech.officetech.API.usersauth.domain.model.commands.CreateSkillCommand;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.CreateSkillResource;

public class CreateSkillCommandFromResourceAssembler {
    public static CreateSkillCommand toCommandFromResource(CreateSkillResource resource) {
        return new CreateSkillCommand(resource.userId(), resource.skillDescription());
    }
}
