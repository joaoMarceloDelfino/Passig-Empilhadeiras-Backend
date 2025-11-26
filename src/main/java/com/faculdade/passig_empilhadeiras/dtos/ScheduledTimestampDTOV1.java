package com.faculdade.passig_empilhadeiras.dtos;

import java.time.LocalTime;

public class ScheduledTimestampDTOV1 {
    private LocalTime initialTime;
    private LocalTime finalTime;

    public LocalTime getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(LocalTime initialTime) {
        this.initialTime = initialTime;
    }

    public LocalTime getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }
}
