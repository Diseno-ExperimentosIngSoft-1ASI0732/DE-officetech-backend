package com.officetech.officetech.API.usersauth.application.internal.commandservices;

import com.officetech.officetech.API.usersauth.domain.model.commands.CreateSkillCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.services.SkillCommandService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.SkillRepository;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;

import java.util.Optional;

@Service
public class SkillCommandServiceImpl implements SkillCommandService {

    private final UserAuthRepository userAuthRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public SkillCommandServiceImpl(UserAuthRepository userAuthRepository, SkillRepository skillRepository) {
        this.userAuthRepository = userAuthRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public Optional<Skill> handle(CreateSkillCommand command) {
        Optional<UserAuth> userOpt = userAuthRepository.findById(command.userId());
        if (userOpt.isEmpty()) throw new IllegalArgumentException("User not found");

        Skill skill = new Skill(command);
        skillRepository.save(skill);
        return Optional.of(skill);
    }


    @Override
    public Boolean handle(Long skillId) {
        Optional<Skill> skillOpt = skillRepository.findById(skillId);
        if (skillOpt.isEmpty()) throw new IllegalArgumentException("Skill not found");

        Skill skill = skillOpt.get();
        skillRepository.delete(skill);
        return true;
    }
}
