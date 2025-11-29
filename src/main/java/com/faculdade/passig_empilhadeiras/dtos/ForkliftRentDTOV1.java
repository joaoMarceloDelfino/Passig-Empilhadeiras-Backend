package com.faculdade.passig_empilhadeiras.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class ForkliftRentDTOV1 {
    @NotNull
    private OffsetDateTime initialScheduledTime;
    @NotNull
    private OffsetDateTime endScheduledTime;
    @NotNull
    private ForkiliftDtoV1 forkiliftDtoV1;
    private String description;

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

    public ForkiliftDtoV1 getForkiliftDtoV1() {
        return forkiliftDtoV1;
    }

    public void setForkiliftDtoV1(ForkiliftDtoV1 forkiliftDtoV1) {
        this.forkiliftDtoV1 = forkiliftDtoV1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
