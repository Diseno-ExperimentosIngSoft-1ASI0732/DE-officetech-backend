package com.officetech.officetech.API.forum.interfaces.rest.resources;

public record GetPostResource(Long postId, String title, String description, Long companyId) {
}
