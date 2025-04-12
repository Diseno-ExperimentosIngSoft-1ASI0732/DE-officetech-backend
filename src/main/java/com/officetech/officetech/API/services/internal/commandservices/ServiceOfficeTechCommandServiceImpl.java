package com.officetech.officetech.API.services.internal.commandservices;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.model.commands.EditServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechCommandService;
import com.officetech.officetech.API.services.infrastructure.persistence.repositories.ServiceOfficeTechRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceOfficeTechCommandServiceImpl implements ServiceOfficeTechCommandService {
    ServiceOfficeTechRepository serviceOfficeTechRepository;
    public ServiceOfficeTechCommandServiceImpl(ServiceOfficeTechRepository serviceOfficeTechRepository) {
        this.serviceOfficeTechRepository = serviceOfficeTechRepository;
    }

    @Override
    public boolean handle(CreateNewServiceOfficeTechCommand command) {
        ServiceOfficeTech serviceOfficeTech = new ServiceOfficeTech(command);
        try {
            this.serviceOfficeTechRepository.save(serviceOfficeTech);
            return true;
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean handle(EditServiceOfficeTechCommand command) {
        Optional<ServiceOfficeTech> serviceOfficeTech = this.serviceOfficeTechRepository.findById(command.serviceId());
        if(serviceOfficeTech.isPresent()) {
            try {
                // Update the service office tech with command.comment, command.rating
                serviceOfficeTech.get().setComment(command.comment());
                serviceOfficeTech.get().setRating(command.rating());
                System.out.println("ServiceOfficeTech: " + serviceOfficeTech.get());
                this.serviceOfficeTechRepository.save(serviceOfficeTech.get());

                return true;
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }


    @Override
    public boolean handle(Long serviceId, String status) {
        Optional<ServiceOfficeTech> serviceOfficeTech = this.serviceOfficeTechRepository.findById(serviceId);
        if(serviceOfficeTech.isPresent()) {
            try {
                // Update the service office tech with status
                serviceOfficeTech.get().setStatusValue(status);
                System.out.println("ServiceOfficeTech: " + serviceOfficeTech.get());
                this.serviceOfficeTechRepository.save(serviceOfficeTech.get());

                return true;
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }
}
