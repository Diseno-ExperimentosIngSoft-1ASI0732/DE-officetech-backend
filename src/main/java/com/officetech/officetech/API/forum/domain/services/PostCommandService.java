package com.officetech.officetech.API.forum.domain.services;

import com.officetech.officetech.API.forum.domain.model.commands.CreateNewPostCommand;

public interface PostCommandService {
    boolean handle(CreateNewPostCommand command);
}
