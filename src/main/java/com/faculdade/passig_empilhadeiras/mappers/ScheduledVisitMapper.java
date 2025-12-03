package com.faculdade.passig_empilhadeiras.mappers;

import com.faculdade.passig_empilhadeiras.dtos.ScheduledVisitDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.ScheduledVisitDTOV2;
import com.faculdade.passig_empilhadeiras.models.ScheduledVisit;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledVisitMapper {

    @Autowired
    private ForkliftMapper forkliftMapper;
    @Resource
    private UserMapper userMapper;

    public ScheduledVisitDTOV1 convertoToDto(ScheduledVisit scheduledVisit) {
        ScheduledVisitDTOV1 scheduledVisitDTO = new ScheduledVisitDTOV1();
        scheduledVisitDTO.setId(scheduledVisit.getId());
        scheduledVisitDTO.setEndScheduledTime(scheduledVisit.getEndScheduledTime());
        scheduledVisitDTO.setInitialScheduledTime(scheduledVisit.getInitialScheduledTime());
        if(scheduledVisit.getDescription() != null) {
            scheduledVisitDTO.setDescription(scheduledVisit.getDescription());
        }
        scheduledVisitDTO.setCompleted(scheduledVisit.getCompleted());
        scheduledVisitDTO.setType(scheduledVisit.getType());
        if(scheduledVisit.getForklift() != null) {
            scheduledVisitDTO.setForklift(forkliftMapper.convertToDto(scheduledVisit.getForklift()));
        }
        return scheduledVisitDTO;
    }

    public ScheduledVisitDTOV2 convertoToDtoV2(ScheduledVisit scheduledVisit) {
        ScheduledVisitDTOV2 scheduledVisitDTO = new ScheduledVisitDTOV2();
        scheduledVisitDTO.setId(scheduledVisit.getId());
        scheduledVisitDTO.setEndScheduledTime(scheduledVisit.getEndScheduledTime());
        scheduledVisitDTO.setInitialScheduledTime(scheduledVisit.getInitialScheduledTime());
        if(scheduledVisit.getDescription() != null) {
            scheduledVisitDTO.setDescription(scheduledVisit.getDescription());
        }
        scheduledVisitDTO.setCompleted(scheduledVisit.getCompleted());
        scheduledVisitDTO.setType(scheduledVisit.getType());
        if(scheduledVisit.getForklift() != null) {
            scheduledVisitDTO.setForklift(forkliftMapper.convertToDto(scheduledVisit.getForklift()));
        }

        scheduledVisitDTO.setUser(userMapper.convertToDTO(scheduledVisit.getIdUser()));

        return scheduledVisitDTO;
    }
}
