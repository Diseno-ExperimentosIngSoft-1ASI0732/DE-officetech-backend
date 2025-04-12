package com.officetech.officetech.API.forum.domain.model.commands;

public record CreateNewAnswerCommand(Long idPost, String description, Long idTechnician) {
}
