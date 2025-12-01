package com.faculdade.passig_empilhadeiras.repositories;

import com.faculdade.passig_empilhadeiras.enums.VisitType;
import com.faculdade.passig_empilhadeiras.models.ScheduledVisit;
import com.faculdade.passig_empilhadeiras.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduledVisitRepository extends JpaRepository<ScheduledVisit, Integer> {

    @Query(value = "SELECT * " +
            "FROM scheduled_visit sv " +
            "WHERE sv.initial_scheduled_time::date = :date", nativeQuery = true)
    List<ScheduledVisit> findByDate(@Param("date") LocalDate date);

    List<ScheduledVisit> findAllByIdUserAndType(User user, VisitType type);
}
