package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.enums.VisitType;
import com.faculdade.passig_empilhadeiras.models.ScheduledVisit;
import com.faculdade.passig_empilhadeiras.models.VisitAttachment;
import com.faculdade.passig_empilhadeiras.repositories.ScheduledVisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ScheduledVisitService {

    @Autowired
    private ScheduledVisitRepository scheduledVisitRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private VisitAttachmentService visitAttachmentService;

    @Transactional
    public Boolean saveScheduledVisit(OffsetDateTime initialScheduledTime, OffsetDateTime endScheduledTime, String description, List<MultipartFile> visitAttachments){
        ScheduledVisit scheduledVisit = new ScheduledVisit();
        scheduledVisit.setInitialScheduledTime(initialScheduledTime)
                .setEndScheduledTime(endScheduledTime)
                .setDescription(description)
                .setIdUser(userService.getLoggedUser())
                .setIsCompleted(false)
                .setType(VisitType.MA);

        ScheduledVisit savedScheduledVisit = scheduledVisitRepository.saveAndFlush(scheduledVisit);

        if (visitAttachments != null && !visitAttachments.isEmpty()) {
            for (MultipartFile file : visitAttachments) {
                String fileAbsolutePath = fileService.handleFileUpload(file, "visit_attachments");
                VisitAttachment visitAttachment = new VisitAttachment();
                visitAttachment.setIdScheduledVisit(savedScheduledVisit);
                visitAttachment.setFileUrl(fileAbsolutePath);
                visitAttachmentService.save(visitAttachment);
            }
        }

        return true;
    }
}
