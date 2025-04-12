package com.officetech.officetech.API.services.domain.services;

import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.model.commands.EditServiceOfficeTechCommand;

import java.util.Optional;

public interface ServiceOfficeTechCommandService {
    boolean handle(CreateNewServiceOfficeTechCommand command);
    boolean handle(EditServiceOfficeTechCommand command);
    boolean handle(Long serviceId, String status);
}
