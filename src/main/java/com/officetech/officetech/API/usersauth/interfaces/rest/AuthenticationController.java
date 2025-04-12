package com.officetech.officetech.API.usersauth.interfaces.rest;

/**
* REST controller for managing authentication.
* <p>This controller provides endpoints for authenticating users.
* It handles HTTP requests and delegates to the appropriate services.</p>
* @author Fabia Herrera
 * @version 1.0
* */

import com.officetech.officetech.API.usersauth.application.internal.commandservices.SkillCommandServiceImpl;
import com.officetech.officetech.API.usersauth.application.internal.queryservices.SkillQueryServiceImpl;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.queries.AuthUserQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetAllSkillsQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByEmailQuery;
import com.officetech.officetech.API.usersauth.domain.model.queries.GetUserByIdQuery;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Password;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthCommandService;
import com.officetech.officetech.API.usersauth.domain.services.UserAuthQueryService;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.*;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.CreateSkillCommandFromResourceAssembler;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.CreateUserAuthCommandFromResourceAssembler;
import com.officetech.officetech.API.usersauth.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {
    private final UserAuthCommandService userAuthCommandService;
    private final UserAuthQueryService userAuthQueryService;
    private final SkillQueryServiceImpl skillQueryService;
    @Autowired
    private SkillCommandServiceImpl skillCommandService;


    public AuthenticationController(UserAuthCommandService userAuthCommandService, UserAuthQueryService userAuthQueryService, SkillQueryServiceImpl skillQueryService) {
        this.userAuthCommandService = userAuthCommandService;
        this.userAuthQueryService = userAuthQueryService;
        this.skillQueryService = skillQueryService;
    }
    /**
     * POST /register : Register a new user.
     * <p>
     *     This endpoint is used to register a new user.
     *     It receives a JSON object with the user's email and password.
     *     It returns a JSON object with the status code, message and the user's id and role.
     *     If the user's email already exists in the database, it returns a JSON object with the status code and message.
     *     If the user's email does not exist in the database, it returns a JSON object with the status code, message and the user's id and role.
     * </p>
     * @param resource the JSON object with the user's email and password
     * @author Fabia Herrera
     * edit: Arian Rodriguez
     * */
    @PostMapping("/register")
    public ValidateUserAuthResource registerNewUser(@RequestBody CreateUserAuthResource resource) {
        // to verify if the user's email already exists in the database
        GetUserByEmailQuery query = new GetUserByEmailQuery(new Email(resource.email()));
        var userExists = userAuthQueryService.handle(query);
        if(userExists.isPresent()) {
            System.out.println("User already exists");
            // return a body like a : {"status_code: 400", "message": "User already exists}
            return new ValidateUserAuthResource(400, "User already exists", null);

        }
        else {
            System.out.println("Registering new user");
            var command = CreateUserAuthCommandFromResourceAssembler.toCommandFromResource(resource);
            var validation = new ValidateUserAuthCommand(command);
            var result = validation.validate();
            if(!Objects.equals(result, "")) {
                System.out.println("Error while validating user entity");
                return new ValidateUserAuthResource(400, result, null);
            }

            Optional<UserAuth> user = userAuthCommandService.handle(command);
            if(user.isEmpty()) {
                System.out.println("Error while saving user entity");
                // return a body like a : {"status_code: 500", "message": "Error while saving user entity}
                return new ValidateUserAuthResource(500, "Error while saving user entity", null);
            }
            return new ValidateUserAuthResource(202, "User created successfully", new GetCreateUserAuthResource(user.get().getId(), user.get().getRole()));
        }
    }

    /**
     * GET /login : Log in a user.
     * <p>
     *     This endpoint is used to log in a user.
     *     It receives a JSON object with the user's email and password.
     *     It returns a JSON object with the status code, message and the user's id and role.
     *     If the user's email does not exist in the database, it returns a JSON object with the status code and message.
     *     If the user's password is incorrect, it returns a JSON object with the status code and message.
     * </p>
     * @param email the user's email
     * @param password the user's password
     * @return a JSON object with the status code, message and the user's id and role
     * */
    @GetMapping("/login")
    public GetUserAuthResource existsUser(String email, String password) {
        System.out.println("Logging in user");
        GetUserByEmailQuery query = new GetUserByEmailQuery(new Email(email));
        var user = userAuthQueryService.handle(query);
        if(user.isEmpty()) {
            System.out.println("User not found");
            return new GetUserAuthResource(404, "User not found", null);
        }

        AuthUserQuery authUserQuery = new AuthUserQuery(new Email(email), new Password(password));
        boolean resultMatch = userAuthQueryService.handle(authUserQuery);
        if(!resultMatch) {
            System.out.println("Password incorrect");
            return new GetUserAuthResource(400, "Password incorrect", null);
        }

        System.out.println("Logged!");

        return new GetUserAuthResource(202, "Logged", UserAuthResourceFromEntityAssembler.toResourceFromEntity(user.get()));

    }

    /**
     * This method is used to get a user by its id.
     * @param userId the user's id
     *               @return a JSON object with the user's id, email and role
     *
     * */

    @GetMapping("/{userId}")
    public ResponseEntity<UserAuth> getUserById(@PathVariable Long userId) {
        Optional<UserAuth> user = userAuthQueryService.handle(new GetUserByIdQuery(userId));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This method is to get all the users.
     * @return a JSON object with the user's id, email and role
     * */

    @GetMapping("/skills/{userId}")
    public ResponseEntity<?> getSkillsByUserId(@PathVariable Long userId) {
        try {
            List<Skill> skills = skillQueryService.getSkillsByUserId(userId);
            return ResponseEntity.ok(skills);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/skills")
    public ResponseEntity<?> addSkillToUser(@RequestBody CreateSkillResource resource) {
        System.out.println(resource);
        try {
            var command = CreateSkillCommandFromResourceAssembler.toCommandFromResource(resource);
            Optional<Skill> skill = skillCommandService.handle(command);
            if(skill.isEmpty()) {
                return ResponseEntity.badRequest().body("Error while saving skill entity");
            }

            return ResponseEntity.status(CREATED).body(skill.get());
        }catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/skills/{skillId}")
    public ResponseEntity<UserAuth> removeSkillFromUser(@PathVariable Long skillId) {
        if(skillCommandService.handle(skillId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/skills")
    public ResponseEntity<?> getAllSkills() {
        try {
            List<Skill> skills = skillQueryService.handle(new GetAllSkillsQuery());
            return ResponseEntity.ok(skills);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
