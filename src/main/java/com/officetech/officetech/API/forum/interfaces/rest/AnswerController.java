package com.officetech.officetech.API.forum.interfaces.rest;

import com.officetech.officetech.API.forum.domain.model.queries.GetAllAnswersByIdPostQuery;
import com.officetech.officetech.API.forum.domain.services.AnswerCommandService;
import com.officetech.officetech.API.forum.domain.services.AnswerQueryService;
import com.officetech.officetech.API.forum.interfaces.rest.resources.CreateNewAnswerResource;
import com.officetech.officetech.API.forum.interfaces.rest.resources.GetAnswerResource;
import com.officetech.officetech.API.forum.interfaces.rest.transform.AnswerResourceFromEntityAssembler;
import com.officetech.officetech.API.forum.interfaces.rest.transform.CreateNewAnswerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/answers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Forum answers")
public class AnswerController {
    private final AnswerCommandService answerCommandService;
    private final AnswerQueryService answerQueryService;

    public AnswerController(AnswerCommandService answerCommandService, AnswerQueryService answerQueryService) {
        this.answerCommandService = answerCommandService;
        this.answerQueryService = answerQueryService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createAnswer(@RequestBody CreateNewAnswerResource resource) {
        var command = CreateNewAnswerCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean response = answerCommandService.handle(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{idPost}")
    public ResponseEntity<List<GetAnswerResource>> getAnswersByPostId(@PathVariable Long idPost) {
        var query = new GetAllAnswersByIdPostQuery(idPost);
        var answers = answerQueryService.handle(query);
        var answerResources = answers.stream().map(AnswerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(answerResources);
    }
}
