package com.officetech.officetech.API.forum.internal.commandservices;

import com.officetech.officetech.API.forum.domain.model.aggregates.Post;
import com.officetech.officetech.API.forum.domain.model.commands.CreateNewPostCommand;
import com.officetech.officetech.API.forum.domain.services.PostCommandService;
import com.officetech.officetech.API.forum.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostCommandServiceImpl implements PostCommandService {
    private final PostRepository postRepository;
    public PostCommandServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean handle(CreateNewPostCommand command) {
        try {
            Post post = new Post(command);
            postRepository.save(post);
            return true;
        }catch(Exception e) {
            System.out.println("Error to save the post: " + e.getMessage());
            return false;
        }
    }
}
