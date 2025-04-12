package com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByUserId(Long userId);

}