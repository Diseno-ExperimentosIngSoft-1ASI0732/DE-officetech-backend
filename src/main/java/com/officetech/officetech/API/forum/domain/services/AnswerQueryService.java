package com.officetech.officetech.API.forum.domain.services;

import com.officetech.officetech.API.forum.domain.model.aggregates.Answer;
import com.officetech.officetech.API.forum.domain.model.queries.GetAllAnswersByIdPostQuery;

import java.util.List;

public interface AnswerQueryService {
    List<Answer> handle(GetAllAnswersByIdPostQuery query);
}
