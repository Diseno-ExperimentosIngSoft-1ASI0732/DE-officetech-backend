package com.officetech.officetech.API.forum.interfaces.rest.transform;

import com.officetech.officetech.API.forum.domain.model.aggregates.Answer;
import com.officetech.officetech.API.forum.interfaces.rest.resources.GetAnswerResource;

public class AnswerResourceFromEntityAssembler {
    public static GetAnswerResource toResourceFromEntity(Answer answer) {
        return new GetAnswerResource(
            answer.getId(),
            answer.getDescription(),
            answer.getIdTechnician(),
            answer.getIdPost(),
            answer.getCreatedAt().toString()
        );
    }
}
