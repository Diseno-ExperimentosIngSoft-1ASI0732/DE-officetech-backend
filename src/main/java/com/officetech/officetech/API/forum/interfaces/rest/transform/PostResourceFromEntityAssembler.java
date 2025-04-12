package com.officetech.officetech.API.forum.interfaces.rest.transform;

import com.officetech.officetech.API.forum.domain.model.aggregates.Post;
import com.officetech.officetech.API.forum.interfaces.rest.resources.GetPostResource;

public class PostResourceFromEntityAssembler {
    public static GetPostResource toResourceFromEntity(Post post) {
        return new GetPostResource(post.getId(), post.getTitle(), post.getDescription(), post.getIdCompany());
    }
}
