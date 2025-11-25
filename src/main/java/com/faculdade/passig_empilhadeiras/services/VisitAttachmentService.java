package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.models.VisitAttachment;
import com.faculdade.passig_empilhadeiras.repositories.VisitAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitAttachmentService {

    @Autowired
    private VisitAttachmentRepository visitAttachmentRepository;

    public VisitAttachment save(VisitAttachment visitAttachment){
        return visitAttachmentRepository.save(visitAttachment);
    }
}
