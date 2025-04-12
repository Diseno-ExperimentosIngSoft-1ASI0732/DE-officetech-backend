package com.officetech.officetech.API.services.interfaces.rest;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.services.domain.model.queries.*;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechCommandService;
import com.officetech.officetech.API.services.domain.services.ServiceOfficeTechQueryService;
import com.officetech.officetech.API.services.interfaces.rest.resources.CreateNewServiceOfficeTechResource;
import com.officetech.officetech.API.services.interfaces.rest.resources.EditServiceOfficeTechResource;
import com.officetech.officetech.API.services.interfaces.rest.resources.UserResource;
import com.officetech.officetech.API.services.interfaces.rest.transform.CreateServiceOfficeTechCommandFromResourceAssembler;
import com.officetech.officetech.API.services.interfaces.rest.transform.CreateUserResourceFromEntity;
import com.officetech.officetech.API.services.interfaces.rest.transform.EditServiceOfficeTechCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller is the entry point for the services context.
 * It exposes the endpoints to interact with the services.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts.
 * @author Arian Rodriguez and Marcelo Ramirez
 * @version 1.0
 * */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Services Management Endpoints")
public class ServicesOfficeTechController {
    private final ServiceOfficeTechCommandService serviceOfficeTechCommandService;
    private final ServiceOfficeTechQueryService serviceOfficeTechQueryService;
    public ServicesOfficeTechController(ServiceOfficeTechCommandService serviceOfficeTechCommandService, ServiceOfficeTechQueryService serviceOfficeTechQueryService) {
        this.serviceOfficeTechCommandService = serviceOfficeTechCommandService;
        this.serviceOfficeTechQueryService = serviceOfficeTechQueryService;
    }

    /**
    * POST api/v1/services
    * Endpoint that creates a service, the company request a new tech service
    * @param resource the resource with the information to create the service
    * @return the response of the service creation
    * */
    @PostMapping
    public ResponseEntity<Response> createService(@RequestBody CreateNewServiceOfficeTechResource resource) {
        var command = CreateServiceOfficeTechCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean result = serviceOfficeTechCommandService.handle(command);
        if (!result) { // if the service was not created
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * PUT api/v1/services/{serviceId}
     * endpoint: http://localhost:8080/api/v1/services/serviceId?comment=' '&rating=' '
     * Endpoint that updates a service the company edit a comment or rating of his services history
     * @param serviceId id of the service to update
     * @param comment comment new comment to add to the service
     * @param rating rating new rating to add to the service
     * */

    @PutMapping("/{serviceId}")
    public ResponseEntity<Response> updateService(@PathVariable Long serviceId, @Param("comment") String comment, @Param("rating") Integer rating) {
        var resource = new EditServiceOfficeTechResource(comment, rating, serviceId);
        var command = EditServiceOfficeTechCommandFromResourceAssembler.toCommandFromResource(resource);

        boolean result = serviceOfficeTechCommandService.handle(command);
        if (!result) { // if the service was not updated
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * PUT api/v1/services/{serviceId}?status=' '
     * endpoint: http://localhost:8080/api/v1/services/serviceId?status=' '
     * Endpoint that updates a service the company edit the status of his services history
     * @param serviceId id of the service to update
     * @param status status new status to add to the service
     * */
    @PutMapping("/change-status/{serviceId}")
    public ResponseEntity<Response> updateServiceStatus(@PathVariable Long serviceId, @Param("status") String status) {
        boolean result = serviceOfficeTechCommandService.handle(serviceId, status);
        if (!result) { // if the service was not updated
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * GET api/v1/services/company/active/{companyId}
     * Endpoint to obtain the services of a company that requested them with status service IN PROGRESS, ACTIVE
     * @param companyId id of the company to get the services
     *
    * */
    @GetMapping("/company/active/{companyId}")
    public List<ServiceOfficeTech> getActiveServicesCompany(@PathVariable Long companyId) {
        var query = new GetServicesOfficeTechByCompanyIdQuery(companyId);
        return serviceOfficeTechQueryService.handle(query, 1);
    }

    /**
     * GET api/v1/services/company/active/{companyId}
     * Endpoint to obtain the services of a company that requested them with status service CANCELLED OR COMPLETED
     * @param companyId id of the company to get the services
     *
     * */
    @GetMapping("/company/completed/{companyId}")
    public List<ServiceOfficeTech> getCompletedServicesCompany(@PathVariable Long companyId) {
        var query = new GetServicesOfficeTechByCompanyIdQuery(companyId);
        return serviceOfficeTechQueryService.handle(query, 2);
    }

    /**
     * GET api/v1/services/technician/active/{technicianId}
     * Endpoint to obtain the services of a technician that requested them with status service IN PROGRESS, ACTIVE
     * @param technicianId id of the company to get the services
     *
     * */
    @GetMapping("/technician/active/{technicianId}")
    public List<ServiceOfficeTech> getActiveServicesTechnician(@PathVariable Long technicianId) {
        var query = new GetServicesOfficeTechByTechnicianId(technicianId);
        return serviceOfficeTechQueryService.handle(query, 1);
    }

    /**
     * GET api/v1/services/technician/active/{technicianId}
     * Endpoint to obtain the services of a technician that requested them with status service CANCELLED OR COMPLETED
     * @param technicianId id of the company to get the services
     *
     * */
    @GetMapping("/technician/completed/{technicianId}")
    public List<ServiceOfficeTech> getCompletedServicesTechnician(@PathVariable Long technicianId) {
        var query = new GetServicesOfficeTechByTechnicianId(technicianId);
        return serviceOfficeTechQueryService.handle(query, 2);
    }

    /**
     * GET api/v1/services/user/{userId}
     * Endpoint to obtain the user information
     * @param userId id of the user to get the information
    * */
    @GetMapping("/user/{userId}")
    public UserResource getUser(@PathVariable Long userId) {
        var query = new GetUserByIdQuery(userId);
        var user = serviceOfficeTechQueryService.handle(query);
        return user.map(CreateUserResourceFromEntity::toResourceFromEntity).orElse(null);
    }

    @GetMapping("/users/technician")
    public List<UserResource> getUsersTechnician() {
        var query = new GetUsersTechnicianQuery();
        return serviceOfficeTechQueryService.handle(query).stream().map(CreateUserResourceFromEntity::toResourceFromEntity).toList(); // this is to convert the list of UserEntity to a list of UserResource and return it as a list
    }

    @GetMapping("/{idService}")
    public ServiceOfficeTech getServiceById(@PathVariable Long idService) {
        var query = new GetServicesByIdQuery(idService);
        return serviceOfficeTechQueryService.handle(query).orElse(null);
    }
    /*

    // Endpoint para obtener servicios con información del técnico
    @GetMapping("/company/{companyId}/services-with-technician-info")
    public List<ServiceOfficeTech> getServicesWithTechnicianInfo(@PathVariable Long companyId) {
        var query = new GetServicesWithTechnicianInfoQuery(companyId);
        return serviceOfficeTechQueryService.handle(query);
    }

    // Endpoint para obtener servicios con información del técnico y calificaciones
    @GetMapping("/company/{companyId}/services-with-technician-info-and-ratings")
    public List<ServiceOfficeTech> getServicesWithTechnicianInfoAndRatings(@PathVariable Long companyId) {
        var query = new GetServicesWithTechnicianInfoAndRatingsQuery(companyId);
        return serviceOfficeTechQueryService.handle(query);
    }

*/
}
