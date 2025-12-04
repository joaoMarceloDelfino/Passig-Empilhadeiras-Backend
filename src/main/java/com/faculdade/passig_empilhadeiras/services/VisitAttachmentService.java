package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.dtos.VisitAttachmentDTOV1;
import com.faculdade.passig_empilhadeiras.mappers.VisitAttachmentMapper;
import com.faculdade.passig_empilhadeiras.models.ForkliftImage;
import com.faculdade.passig_empilhadeiras.models.VisitAttachment;
import com.faculdade.passig_empilhadeiras.repositories.VisitAttachmentRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitAttachmentService {

    @Autowired
    private VisitAttachmentRepository visitAttachmentRepository;
    @Resource
    private VisitAttachmentMapper  visitAttachmentMapper;

    public VisitAttachment save(VisitAttachment visitAttachment){
        return visitAttachmentRepository.save(visitAttachment);
    }

    public List<VisitAttachmentDTOV1> findAllByVisitId(Integer visitId) {
        return visitAttachmentRepository.findAllByIdScheduledVisitId(visitId).stream()
                .map(x -> visitAttachmentMapper.converToDTO(x)).collect(Collectors.toList());
    }
}
