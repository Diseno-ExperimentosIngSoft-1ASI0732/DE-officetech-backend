package com.officetech.officetech.API.forum.internal.commandservices;

import com.officetech.officetech.API.forum.domain.model.aggregates.Answer;
import com.officetech.officetech.API.forum.domain.model.commands.CreateNewAnswerCommand;
import com.officetech.officetech.API.forum.domain.services.AnswerCommandService;
import com.officetech.officetech.API.forum.repositories.AnswerRepository;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
public class AnswerCommandServiceImpl implements AnswerCommandService {
    private final AnswerRepository answerRepository;
    public AnswerCommandServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public boolean handle(CreateNewAnswerCommand command) {
        try {
            var answer = new Answer(command);
            this.answerRepository.save(answer);
            return true;
        }catch(Exception e) {
            System.out.println("Error to save answer: " + e.getMessage());
            return false;
        }
    }
}
