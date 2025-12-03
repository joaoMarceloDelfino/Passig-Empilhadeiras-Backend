package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.dtos.ForkliftRentDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.ScheduledTimestampDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.ScheduledVisitDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.ScheduledVisitDTOV2;
import com.faculdade.passig_empilhadeiras.enums.ForkliftStatus;
import com.faculdade.passig_empilhadeiras.enums.VisitType;
import com.faculdade.passig_empilhadeiras.mappers.ForkliftMapper;
import com.faculdade.passig_empilhadeiras.mappers.ScheduledVisitMapper;
import com.faculdade.passig_empilhadeiras.models.*;
import com.faculdade.passig_empilhadeiras.repositories.ScheduledVisitRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.*;
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
    @Resource
    private ForkliftMapper forkliftMapper;
    @Autowired
    private ForkliftService forkliftService;
    @Resource
    private ScheduledVisitMapper  scheduledVisitMapper;

    @Transactional
    public Boolean saveScheduledVisit(OffsetDateTime initialScheduledTime, OffsetDateTime endScheduledTime, String description, List<MultipartFile> visitAttachments){

        if(endScheduledTime.isBefore(initialScheduledTime)){
            throw new RuntimeException("Invalid initial scheduled time");
        }

        if(initialScheduledTime.isBefore(OffsetDateTime.now())){
            throw new RuntimeException("initial scheduled time in the past");
        }

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

            LocalDateTime currentInitialDateTime  = LocalDateTime.of(date, currentInitialTime);

            for(ScheduledVisit scheduledVisit : scheduledVisits){
                if((currentInitialTime.isBefore(scheduledVisit.getEndScheduledTime().toLocalTime()) && currentEndTime.isAfter(scheduledVisit.getInitialScheduledTime().toLocalTime()))) {
                    isNotOcupado = false;
                }

            }

            if(currentInitialDateTime.isBefore(LocalDateTime.now()) && isNotOcupado){
                isNotOcupado = false;
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

    public Boolean saveForkliftRentVist(ForkliftRentDTOV1 forkliftRentDTOV1) {

        if(forkliftRentDTOV1.getEndScheduledTime().isBefore(forkliftRentDTOV1.getInitialScheduledTime())){
            throw new RuntimeException("Invalid initial scheduled time");
        }

        if(forkliftRentDTOV1.getInitialScheduledTime().isBefore(OffsetDateTime.now())){
            throw new RuntimeException("initial scheduled time in the past");
        }

        Forklift forklift = forkliftService.findById(forkliftRentDTOV1.getForkiliftDtoV1().getId());

        if(forklift == null){
            throw new RuntimeException("Forklift not found");
        }

        if(!forklift.getStatus().equals(ForkliftStatus.FO)){
            throw new RuntimeException("Forklift indisponible");
        }

        User loggedUser = userService.getLoggedUser();
        ScheduledVisit scheduledVisit = new ScheduledVisit();
        scheduledVisit.setInitialScheduledTime(forkliftRentDTOV1.getInitialScheduledTime());
        scheduledVisit.setEndScheduledTime(forkliftRentDTOV1.getEndScheduledTime());
        scheduledVisit.setType(VisitType.AL);
        scheduledVisit.setIdUser(loggedUser);
        scheduledVisit.setForklift(forkliftMapper.convertToModel(forkliftRentDTOV1.getForkiliftDtoV1()));
        scheduledVisit.setIsCompleted(false);
        if(forkliftRentDTOV1.getDescription() != null){
            scheduledVisit.setDescription(forkliftRentDTOV1.getDescription());
        }

        scheduledVisitRepository.saveAndFlush(scheduledVisit);

        return true;
    }

    public List<ScheduledVisitDTOV1> findAllByType(VisitType visitType){
        User user = userService.getLoggedUser();
        return scheduledVisitRepository.findAllByIdUserAndType(user, visitType)
                .stream()
                .map(x -> scheduledVisitMapper.convertoToDto(x)).toList();
    }

    public List<ScheduledVisitDTOV2> findALl(){
        return scheduledVisitRepository.findAll()
                .stream()
                .map(x -> scheduledVisitMapper.convertoToDtoV2(x)).toList();
    }
}
