package com.officetech.officetech.API.forum.domain.services;

import com.officetech.officetech.API.forum.domain.model.commands.CreateNewAnswerCommand;

public interface AnswerCommandService {
    boolean handle(CreateNewAnswerCommand command);
}
