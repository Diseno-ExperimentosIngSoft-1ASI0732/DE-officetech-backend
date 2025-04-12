package com.officetech.officetech.API.forum.interfaces.rest.transform;

import com.officetech.officetech.API.forum.domain.model.commands.CreateNewPostCommand;
import com.officetech.officetech.API.forum.interfaces.rest.resources.CreateNewPostResource;

public class CreateNewPostCommandFromResourceAssembler {
    public static CreateNewPostCommand toCommandFromResource(CreateNewPostResource resource) {
        return new CreateNewPostCommand(resource.idCompany(), resource.title(), resource.description());
    }
}
