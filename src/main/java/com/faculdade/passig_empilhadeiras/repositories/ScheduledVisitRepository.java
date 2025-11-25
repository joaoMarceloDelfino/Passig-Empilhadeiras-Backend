package com.faculdade.passig_empilhadeiras.repositories;

import com.faculdade.passig_empilhadeiras.models.ScheduledVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledVisitRepository extends JpaRepository<ScheduledVisit, Integer> {
}
