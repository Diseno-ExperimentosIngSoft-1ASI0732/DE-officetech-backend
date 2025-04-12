package com.officetech.officetech.API.services.domain.services;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.*;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;

import java.util.List;
import java.util.Optional;

public interface ServiceOfficeTechQueryService {
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByCompanyIdQuery query, Integer option);
    List<ServiceOfficeTech> handle(GetServicesOfficeTechByTechnicianId query, Integer option);
    Optional<UserAuth> handle(GetUserByIdQuery query);

    List<UserAuth> handle(GetUsersTechnicianQuery query);

    Optional<ServiceOfficeTech> handle(GetServicesByIdQuery query);
    /*
    List<ServiceOfficeTech> handle(GetServicesWithTechnicianInfoQuery query);
    List<ServiceOfficeTech> handle(GetServicesWithTechnicianInfoAndRatingsQuery query);*/
}
