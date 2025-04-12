package com.officetech.officetech.API.usersauth.interfaces.rest.transform;


import com.officetech.officetech.API.usersauth.domain.model.commands.CreateUserAuthCommand;
import com.officetech.officetech.API.usersauth.interfaces.rest.resources.CreateUserAuthResource;

/*
* Assembler for converting CreateUserAuthResource objects to CreateUserAuthCommand commands.
* <p>This assembler provides a method to convert a CreateUserAuthResource object, which is typically used
* in API requests, to a CreateUserAuthCommand, which is used to execute business logic.</p>
*
* */
public class CreateUserAuthCommandFromResourceAssembler {
    public static CreateUserAuthCommand toCommandFromResource(CreateUserAuthResource resource) {
        System.out.println("CreateUserAuthCommandFromResourceAssembler: converting resource to command");
        return new CreateUserAuthCommand(resource.firstName(), resource.lastName(), resource.email(), resource.password(), resource.role());
    }
}
