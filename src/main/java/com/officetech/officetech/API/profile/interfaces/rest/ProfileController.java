package com.officetech.officetech.API.profile.interfaces.rest;

import com.officetech.officetech.API.profile.domain.model.commands.UpdateProfileCommand;
import com.officetech.officetech.API.profile.interfaces.rest.resources.ProfileResource;
import com.officetech.officetech.API.profile.interfaces.rest.transform.ProfileCommandFromResourceAssembler;
import com.officetech.officetech.API.profile.domain.services.ProfileCommandService;
import com.officetech.officetech.API.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * This method is to edit a profile by id
 * and return the updated profile
 * @author Vladimir Jara
 * @version 1.0
 * */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profile", description = "Profile API")
public class ProfileController {

    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileCommandService profileCommandService) {
        this.profileCommandService = profileCommandService;
    }

    /**
     * This method is to edit a profile by id
     * and return the updated profile
     * @param resource ProfileResource
     *                 The profile resource
     *                 to be updated
     *                 in the database
     *                 not null
     * */
    @PutMapping("/{id}")
    public ResponseEntity<?> editProfile(@RequestBody ProfileResource resource) {
        try {
            UpdateProfileCommand command = ProfileCommandFromResourceAssembler.toCommandFromResource(resource);
            Optional<UserAuth> newProfile = profileCommandService.handle(command);
            if(newProfile.isEmpty()) {
                return ResponseEntity.badRequest().body("Profile not found");
            }

            return ResponseEntity.status(CREATED).body(ProfileResourceFromEntityAssembler.toResourceFromEntity(newProfile.get()));
        }catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}