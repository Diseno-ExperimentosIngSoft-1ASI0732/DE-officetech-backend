package com.officetech.officetech.API.forum.interfaces.acl;

import com.officetech.officetech.API.forum.domain.model.commands.CreateNewPostCommand;
import com.officetech.officetech.API.forum.domain.services.PostCommandService;

/**
 * This class is to be used by other contexts to interact with the Forum context.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts.
 * */
public class ForumContextFacade {
    public PostCommandService postCommandService;

    public ForumContextFacade(PostCommandService postCommandService) {
        this.postCommandService = postCommandService;
    }

    /**
     * This method creates a new post.
     *
     * @param title the title of the post
     *              the title of the post
     * */
    public Long createPost(Long idCompany, String title, String description) {
        var command = new CreateNewPostCommand(idCompany, title, description);
        var response = postCommandService.handle(command);
        if(response) return 1L;
        return 0L;
    }
}
