package com.faculdade.passig_empilhadeiras.repositories;

import com.faculdade.passig_empilhadeiras.models.VisitAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitAttachmentRepository extends JpaRepository<VisitAttachment, Integer> {

    List<VisitAttachment> findAllByIdScheduledVisitId(Integer visitId);
}
