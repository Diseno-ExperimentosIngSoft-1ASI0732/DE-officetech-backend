package com.officetech.officetech.API.forum.domain.services;

import com.officetech.officetech.API.forum.domain.model.aggregates.Post;
import com.officetech.officetech.API.forum.domain.model.queries.GetAllPostsQuery;
import com.officetech.officetech.API.forum.domain.model.queries.GetPostByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PostQueryService {
    List<Post> handle(GetAllPostsQuery query);
    Optional<Post> handle(GetPostByIdQuery query);
}
