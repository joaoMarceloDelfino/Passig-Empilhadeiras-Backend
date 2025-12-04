package com.faculdade.passig_empilhadeiras.mappers;

import com.faculdade.passig_empilhadeiras.dtos.VisitAttachmentDTOV1;
import com.faculdade.passig_empilhadeiras.models.VisitAttachment;
import com.faculdade.passig_empilhadeiras.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitAttachmentMapper {

    @Autowired
    FileService fileService;

    public VisitAttachmentDTOV1 converToDTO(VisitAttachment visitAttachment) {
        VisitAttachmentDTOV1 dto = new VisitAttachmentDTOV1();
        dto.setId(visitAttachment.getId());
        dto.setBase64Url(fileService.encodeToBase64(visitAttachment.getFileUrl()));
        dto.setExtension(visitAttachment.getFileUrl().substring(visitAttachment.getFileUrl().lastIndexOf(".")));
        return dto;
    }
}
