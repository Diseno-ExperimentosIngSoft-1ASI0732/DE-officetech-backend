package com.officetech.officetech.API.usersauth.domain.model.aggregates;

import com.officetech.officetech.API.usersauth.domain.model.commands.CreateSkillCommand;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Long userId;

    @Getter
    private String name;

    protected Skill() {}

    public Skill(CreateSkillCommand command) {
        this.userId = command.userId();
        this.name = command.skillDescription();
    }
}
