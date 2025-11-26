package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.dtos.ScheduledTimestampDTOV1;
import com.faculdade.passig_empilhadeiras.enums.VisitType;
import com.faculdade.passig_empilhadeiras.models.Parametro;
import com.faculdade.passig_empilhadeiras.models.ScheduledVisit;
import com.faculdade.passig_empilhadeiras.models.VisitAttachment;
import com.faculdade.passig_empilhadeiras.repositories.ScheduledVisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
    @Autowired
    private ParametroService parametroService;

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

    public List<ScheduledTimestampDTOV1> findDisponibleScheduledTimestamps(LocalDate date){
        List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByDate(date);
        for(ScheduledVisit scheduledVisit : scheduledVisits){
            scheduledVisit.setInitialScheduledTime(scheduledVisit.getInitialScheduledTime().withOffsetSameInstant(ZoneOffset.of("-03:00")));
            scheduledVisit.setEndScheduledTime(scheduledVisit.getEndScheduledTime().withOffsetSameInstant(ZoneOffset.of("-03:00")));
        }
        List<ScheduledTimestampDTOV1> scheduledTimestamps = new ArrayList<>();
        final LocalTime HORARIO_INICIO_EXPEDIENTE = LocalTime.parse(parametroService.findValueByName("HORARIO_INICIO_EXPEDIENTE", "08:00:00"));
        final LocalTime HORARIO_FIM_EXPEDIENTE = LocalTime.parse(parametroService.findValueByName("HORARIO_FIM_EXPEDIENTE", "18:00:00"));
        final Long DURACAO_ESTIMADA_VISITA = Long.parseLong(parametroService.findValueByName("DURACAO_ESTIMADA_VISITA_HORAS", "2"));

        LocalTime currentInitialTime = HORARIO_INICIO_EXPEDIENTE;
        LocalTime currentEndTime = HORARIO_INICIO_EXPEDIENTE.plusMinutes(DURACAO_ESTIMADA_VISITA * 60);

        while(!currentEndTime.isAfter(HORARIO_FIM_EXPEDIENTE)) {
            boolean isNotOcupado = true;

            for(ScheduledVisit scheduledVisit : scheduledVisits){
                if(currentInitialTime.isBefore(scheduledVisit.getEndScheduledTime().toLocalTime()) && currentEndTime.isAfter(scheduledVisit.getInitialScheduledTime().toLocalTime())) {
                    isNotOcupado = false;
                }

            }

            if(isNotOcupado){
                ScheduledTimestampDTOV1 scheduledTimestampDTOV1 = new ScheduledTimestampDTOV1();
                scheduledTimestampDTOV1.setInitialTime(currentInitialTime);
                scheduledTimestampDTOV1.setFinalTime(currentEndTime);
                scheduledTimestamps.add(scheduledTimestampDTOV1);
            }

            currentInitialTime = currentEndTime;
            currentEndTime = currentInitialTime.plusMinutes(DURACAO_ESTIMADA_VISITA * 60);
        }

        return scheduledTimestamps;
    }
}
