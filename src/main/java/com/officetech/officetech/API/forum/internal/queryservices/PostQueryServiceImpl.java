package com.officetech.officetech.API.forum.internal.queryservices;

import com.officetech.officetech.API.forum.domain.model.aggregates.Post;
import com.officetech.officetech.API.forum.domain.model.queries.GetAllPostsQuery;
import com.officetech.officetech.API.forum.domain.model.queries.GetPostByIdQuery;
import com.officetech.officetech.API.forum.domain.services.PostQueryService;
import com.officetech.officetech.API.forum.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostQueryServiceImpl implements PostQueryService {
    private final PostRepository postQueryRepository;

    public PostQueryServiceImpl(PostRepository postQueryRepository) {
        this.postQueryRepository = postQueryRepository;
    }

    @Override
    public List<Post> handle(GetAllPostsQuery query) {
        try {
            return postQueryRepository.findAll();
        }catch (Exception e) {
            System.out.println("Error to obtain all the posts: " + e.getMessage());
            return List.of();
        }
    }
    @Override
    public Optional<Post> handle(GetPostByIdQuery query) {
        try {
            return postQueryRepository.findById(query.postId());
        }catch(Exception e) {
            System.out.println("Error to obtain the post by id: " + e.getMessage());
            return Optional.empty();
        }
    }
}
