package com.officetech.officetech.API.forum.domain.model.commands;

public record CreateNewPostCommand(Long idCompany, String title, String description) {
}
