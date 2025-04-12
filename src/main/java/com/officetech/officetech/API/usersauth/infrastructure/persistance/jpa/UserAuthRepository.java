package com.officetech.officetech.API.usersauth.infrastructure.persistance.jpa;

import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import com.officetech.officetech.API.usersauth.domain.model.valueobjects.Email;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    boolean existsById(Long id);
    Optional<UserAuth> findUserAuthByEmail(Email email);
    List<UserAuth> findAll();

}
