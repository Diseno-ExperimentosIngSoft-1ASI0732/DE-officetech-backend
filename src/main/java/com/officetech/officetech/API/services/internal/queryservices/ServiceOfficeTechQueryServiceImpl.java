package com.officetech.officetech.API.services.internal.queryservices;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.*;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechQueryService;
import com.officetech.officetech.API.services.infrastructure.persistence.repositories.ServiceOfficeTechRepository;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfficeTechQueryServiceImpl implements ServiceOfficeTechQueryService {
    ServiceOfficeTechRepository serviceOfficeTechRepository;
    public ServiceOfficeTechQueryServiceImpl(ServiceOfficeTechRepository serviceOfficeTechRepository) {
        this.serviceOfficeTechRepository = serviceOfficeTechRepository;
    }

    @Override
    public List<ServiceOfficeTech> handle(GetServicesOfficeTechByCompanyIdQuery query, Integer option) {
        try {
            if(option == 1)
                return serviceOfficeTechRepository.findAllByCompanyIdWhereStatusIsInProgress(query.companyId());
            else
                return serviceOfficeTechRepository.findAllByCompanyIdWhereStatusIsInCompleted(query.companyId());
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<ServiceOfficeTech> handle(GetServicesOfficeTechByTechnicianId query, Integer option) {
        try {
            if(option == 1)
                return serviceOfficeTechRepository.findAllByTechnicianIdWhereStatusIsInProgress(query.technicianId());
            else
                return serviceOfficeTechRepository.findAllByTechnicianIdWhereStatusIsInCompleted(query.technicianId());
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public Optional<UserAuth> handle(GetUserByIdQuery query) {
        try {
            return serviceOfficeTechRepository.findUserById(query.userId());
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<UserAuth> handle(GetUsersTechnicianQuery query) {
        try {
            return serviceOfficeTechRepository.findAllTechnicians();
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public Optional<ServiceOfficeTech> handle(GetServicesByIdQuery query) {
        try {
            return serviceOfficeTechRepository.findServiceOfficeTechById(query.idService());
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Optional.empty();
        }
    }
    /*
    @Override
    public List<ServiceOfficeTech> handle(GetServicesWithTechnicianInfoQuery query) {
        return serviceOfficeTechRepository.findServicesWithTechnicianInfo(query.companyId());
    }

    @Override
    public List<ServiceOfficeTech> handle(GetServicesWithTechnicianInfoAndRatingsQuery query) {
        return serviceOfficeTechRepository.findServicesWithTechnicianInfoAndRatings(query.companyId());
    }*/
}
