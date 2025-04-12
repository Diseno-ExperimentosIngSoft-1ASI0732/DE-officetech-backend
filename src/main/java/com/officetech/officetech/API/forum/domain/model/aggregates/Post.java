package com.officetech.officetech.API.forum.domain.model.aggregates;


import com.officetech.officetech.API.forum.domain.model.commands.CreateNewPostCommand;
import com.officetech.officetech.API.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Post extends AuditableAbstractAggregateRoot<Post> {
    private Long idCompany;
    private String title;
    @Column(length = 5000)
    private String description;

    public Post(){}
    public Post(Long idCompany, String title, String description) {
        this.idCompany = idCompany;
        this.title = title;
        this.description = description;
        this.setUpdatedAt();
        this.setCreatedAt();
    }
    public Post(CreateNewPostCommand command){
        this.idCompany = command.idCompany();
        this.title = command.title();
        this.description = command.description();
        this.setUpdatedAt();
        this.setCreatedAt();
    }
}
