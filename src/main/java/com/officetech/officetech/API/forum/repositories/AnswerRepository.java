package com.officetech.officetech.API.forum.repositories;

import com.officetech.officetech.API.forum.domain.model.aggregates.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM Answer a WHERE a.idPost = ?1")
    List<Answer> findAllByPostId(Long idPost);
}
