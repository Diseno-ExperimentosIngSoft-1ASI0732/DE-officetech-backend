package com.officetech.officetech.API.forum.interfaces.rest.transform;

import com.officetech.officetech.API.forum.domain.model.commands.CreateNewAnswerCommand;
import com.officetech.officetech.API.forum.interfaces.rest.resources.CreateNewAnswerResource;

public class CreateNewAnswerCommandFromResourceAssembler {
    public static CreateNewAnswerCommand toCommandFromResource(CreateNewAnswerResource resource) {
        return new CreateNewAnswerCommand(
                resource.idPost(),
                resource.description(),
                resource.idTechnician()
        );
    }
}
