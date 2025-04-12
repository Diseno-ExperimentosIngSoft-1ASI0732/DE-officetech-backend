package com.officetech.officetech.API.forum.domain.model.aggregates;

import com.officetech.officetech.API.forum.domain.model.commands.CreateNewAnswerCommand;
import com.officetech.officetech.API.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.ansi.AnsiElement;

@Getter
@Entity
public class Answer extends AuditableAbstractAggregateRoot<Answer> {
    @Column(length = 5000)
    private String description;
    private Long idTechnician;
    private Long idPost;

    public Answer(){
        this.description = Strings.EMPTY;
        this.idTechnician = 0L;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public Answer(String description, Long idTechnician, Long idPostd) {
        this.description = description;
        this.idTechnician = idTechnician;
        this.idPost = idPost;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public Answer(CreateNewAnswerCommand command) {
        this.description = command.description();
        this.idTechnician = command.idTechnician();
        this.idPost = command.idPost();
        this.setCreatedAt();
        this.setUpdatedAt();
    }
}
