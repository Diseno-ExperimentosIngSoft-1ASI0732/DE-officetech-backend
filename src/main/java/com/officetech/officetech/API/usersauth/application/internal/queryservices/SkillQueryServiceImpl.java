package com.officetech.officetech.API.usersauth.application.internal.queryservices;

import com.officetech.officetech.API.usersauth.domain.model.queries.GetAllSkillsQuery;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.services.SkillQueryService;
import com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa.UserAuthRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SkillQueryServiceImpl implements SkillQueryService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillQueryServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getSkillsByUserId(Long userId) {
        return skillRepository.findByUserId(userId);
    }

    @Override
    public List<Skill> handle(GetAllSkillsQuery query) {
        return skillRepository.findAll();
    }
}
