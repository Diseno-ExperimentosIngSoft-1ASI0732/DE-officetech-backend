package com.officetech.officetech.API.services.interfaces.rest.transform;

import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.interfaces.rest.resources.CreateNewServiceOfficeTechResource;

public class CreateServiceOfficeTechCommandFromResourceAssembler {
    public static CreateNewServiceOfficeTechCommand toCommandFromResource(CreateNewServiceOfficeTechResource resource) {
        return new CreateNewServiceOfficeTechCommand(resource.title(), resource.description(), resource.estimatePricing(), resource.date(), resource.comment(), resource.companyId(), resource.technicianId(), resource.rating());
    }
}
