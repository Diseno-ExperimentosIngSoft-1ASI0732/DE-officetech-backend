package com.officetech.officetech.API.services.interfaces.acl;

import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechCommandService;

/**
 * This class is to be used by other contexts to interact with the Service context
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts
 * It is used to create a new service
 * */
public class ServicesContextFacade {
    private final ServiceOfficeTechCommandService serviceCommandService;
    public ServicesContextFacade(ServiceOfficeTechCommandService serviceCommandService) {
        this.serviceCommandService = serviceCommandService;
    }

    /**
     * This method creates a new service
     * @param title the title of the service
     *              description the description of the service
     * */
    public Long createService(String title, String description, Float estimatePricing, String date, String comment, Long companyId, Long technicianId, Integer rating) {
        var createServiceCommand = new CreateNewServiceOfficeTechCommand(title, description, estimatePricing, date, comment, companyId, technicianId, rating);
        var service = serviceCommandService.handle(createServiceCommand);
        if (service) return 0L;
        return 1L;
    }

}
