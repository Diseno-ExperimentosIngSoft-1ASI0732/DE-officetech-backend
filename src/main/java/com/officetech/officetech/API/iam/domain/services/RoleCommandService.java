package com.officetech.officetech.API.iam.domain.services;

import com.officetech.officetech.API.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
