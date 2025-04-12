package com.officetech.officetech.API.forum.interfaces.rest.resources;

public record GetAnswerResource(
        Long id,
        String description,
        Long idTechnician,
        Long idPost,
        String createdAt
) {
}
