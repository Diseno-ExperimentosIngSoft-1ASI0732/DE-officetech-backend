package com.officetech.officetech.API.forum.internal.queryservices;

import com.officetech.officetech.API.forum.domain.model.aggregates.Answer;
import com.officetech.officetech.API.forum.domain.model.queries.GetAllAnswersByIdPostQuery;
import com.officetech.officetech.API.forum.domain.services.AnswerQueryService;
import com.officetech.officetech.API.forum.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerQueryServiceImpl implements AnswerQueryService {
    private final AnswerRepository answerRepository;

    public AnswerQueryServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> handle(GetAllAnswersByIdPostQuery query) {
        try {
            return this.answerRepository.findAllByPostId(query.idPost());
        }catch(Exception e) {
            System.out.println("Error to get all answers by post id: " + e.getMessage());
            return List.of();
        }
    }
}
