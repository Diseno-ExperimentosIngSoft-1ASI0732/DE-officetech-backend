package com.officetech.officetech.API.forum.interfaces.rest;
import com.officetech.officetech.API.forum.domain.model.aggregates.Post;
import com.officetech.officetech.API.forum.domain.model.queries.GetAllPostsQuery;
import com.officetech.officetech.API.forum.domain.model.queries.GetPostByIdQuery;
import com.officetech.officetech.API.forum.domain.services.PostCommandService;
import com.officetech.officetech.API.forum.domain.services.PostQueryService;
import com.officetech.officetech.API.forum.interfaces.rest.resources.CreateNewPostResource;
import com.officetech.officetech.API.forum.interfaces.rest.resources.GetPostResource;
import com.officetech.officetech.API.forum.interfaces.rest.resources.GetResponsePostResource;
import com.officetech.officetech.API.forum.interfaces.rest.transform.CreateNewPostCommandFromResourceAssembler;
import com.officetech.officetech.API.forum.interfaces.rest.transform.PostResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This controller is to handle the forum posts.
 * It is used to create new posts and get all posts.
 * It is implemented as part of the forum context.
 * @author Arian Rodriguez
 * @version 1.0
 * */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/forum", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Forum")
public class PostController {
    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    public PostController(PostCommandService postCommandService, PostQueryService postQueryService) {
        this.postCommandService = postCommandService;
        this.postQueryService = postQueryService;
    }

    /**
     * POST method to create a new post.
     * @param resource the resource to create a new post.
     *                 It contains the title and the content of the post.
     *                 It is a JSON object.
     *
     * */
    @PostMapping("/new-post")
    public ResponseEntity<Boolean> createPost(@RequestBody CreateNewPostResource resource) {
        var command = CreateNewPostCommandFromResourceAssembler.toCommandFromResource(resource);
        return ResponseEntity.ok(postCommandService.handle(command));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<GetPostResource>> getAllPosts() {
        var query = new GetAllPostsQuery();
        var posts = postQueryService.handle(query);
        var postResources = posts.stream().map(PostResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(postResources);
    }

    @GetMapping("/posts/{postId}")
    public GetResponsePostResource getPostById(@PathVariable Long postId) {
        var query = new GetPostByIdQuery(postId);
        var post = postQueryService.handle(query);
        if(post.isEmpty()) return new GetResponsePostResource(404, "Post not found", null);
        return new GetResponsePostResource(202, "Post found", PostResourceFromEntityAssembler.toResourceFromEntity(post.get()));
    }
}
