package com.faculdade.passig_empilhadeiras.dtos;

import com.faculdade.passig_empilhadeiras.enums.VisitType;
import com.faculdade.passig_empilhadeiras.models.Forklift;


import java.time.OffsetDateTime;

public class ScheduledVisitDTOV1 {

    private Integer id;

    private VisitType type;

    private OffsetDateTime initialScheduledTime;

    private OffsetDateTime endScheduledTime;

    private ForkiliftDtoV1 forklift;

    private Boolean isCompleted;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VisitType getType() {
        return type;
    }

    public void setType(VisitType type) {
        this.type = type;
    }

    public OffsetDateTime getInitialScheduledTime() {
        return initialScheduledTime;
    }

    public void setInitialScheduledTime(OffsetDateTime initialScheduledTime) {
        this.initialScheduledTime = initialScheduledTime;
    }

    public OffsetDateTime getEndScheduledTime() {
        return endScheduledTime;
    }

    public void setEndScheduledTime(OffsetDateTime endScheduledTime) {
        this.endScheduledTime = endScheduledTime;
    }

    public ForkiliftDtoV1 getForklift() {
        return forklift;
    }

    public void setForklift(ForkiliftDtoV1 forklift) {
        this.forklift = forklift;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
