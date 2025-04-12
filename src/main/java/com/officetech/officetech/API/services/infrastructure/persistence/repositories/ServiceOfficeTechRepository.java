package com.officetech.officetech.API.services.infrastructure.persistence.repositories;

import com.officetech.officetech.API.services.domain.model.aggregates.ServiceOfficeTech;
import com.officetech.officetech.API.usersauth.domain.model.aggregates.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOfficeTechRepository extends JpaRepository<ServiceOfficeTech, Long> {
    List<ServiceOfficeTech> findAllByCompanyId(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.companyId = ?1 AND s.status IN ('In Progress', 'Active')")
    List<ServiceOfficeTech> findAllByCompanyIdWhereStatusIsInProgress(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.companyId = ?1 AND s.status IN ('Cancelled', 'Completed')")
    List<ServiceOfficeTech> findAllByCompanyIdWhereStatusIsInCompleted(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.technicianId = ?1 AND s.status IN ('In Progress', 'Active')")
    List<ServiceOfficeTech> findAllByTechnicianIdWhereStatusIsInProgress(Long technicianId);

    @Query("SELECT s FROM ServiceOfficeTech s WHERE s.technicianId = ?1 AND s.status IN ('Cancelled', 'Completed')")
    List<ServiceOfficeTech> findAllByTechnicianIdWhereStatusIsInCompleted(Long technicianId);

    @Query("SELECT u FROM UserAuth u WHERE u.id = ?1")
    Optional<UserAuth> findUserById(Long userId);

    @Query("SELECT u from UserAuth u WHERE u.role.role = 'technician'")
    List<UserAuth> findAllTechnicians();

    Optional<ServiceOfficeTech> findServiceOfficeTechById(Long id);


    /*
    @Query("SELECT s FROM ServiceOfficeTech s JOIN UserEntity t ON s.technicianId = t.id WHERE s.companyId = :companyId")
    List<ServiceOfficeTech> findServicesWithTechnicianInfo(Long companyId);

    @Query("SELECT s FROM ServiceOfficeTech s JOIN UserEntity t ON s.technicianId = t.id LEFT JOIN Rating r ON s.id = r.serviceId WHERE s.companyId = :companyId")
    List<ServiceOfficeTech> findServicesWithTechnicianInfoAndRatings(Long companyId);*/
}
