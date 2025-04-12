package com.officetech.officetech.API.services.interfaces.rest.transform;

import com.officetech.officetech.API.services.domain.model.commands.EditServiceOfficeTechCommand;
import com.officetech.officetech.API.services.interfaces.rest.resources.EditServiceOfficeTechResource;

public class EditServiceOfficeTechCommandFromResourceAssembler {
    public static EditServiceOfficeTechCommand toCommandFromResource(EditServiceOfficeTechResource resource) {
        return new EditServiceOfficeTechCommand(resource.comment(), resource.rating(), resource.serviceId());
    }
}
