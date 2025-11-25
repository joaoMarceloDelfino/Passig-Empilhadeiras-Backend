package com.faculdade.passig_empilhadeiras.models;

import com.faculdade.passig_empilhadeiras.enums.VisitType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

@Entity
public class ScheduledVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @NotNull
    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private VisitType type;

    @NotNull
    @Column(name = "initial_scheduled_time", nullable = false)
    private OffsetDateTime initialScheduledTime;

    @NotNull
    @Column(name = "end_scheduled_time", nullable = false)
    private OffsetDateTime endScheduledTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_forklift")
    private Forklift forklift;

    @NotNull
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;

    @Column(name = "description")
    private String description;

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public ScheduledVisit setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }

    public Forklift getForklift() {
        return forklift;
    }

    public ScheduledVisit setForklift(Forklift forklift) {
        this.forklift = forklift;
        return this;
    }

    public VisitType getType() {
        return type;
    }

    public ScheduledVisit setType(VisitType type) {
        this.type = type;
        return this;
    }

    public User getIdUser() {
        return idUser;
    }

    public ScheduledVisit setIdUser(User idUser) {
        this.idUser = idUser;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ScheduledVisit setId(Integer id) {
        this.id = id;
        return this;
    }

    public OffsetDateTime getInitialScheduledTime() {
        return initialScheduledTime;
    }

    public ScheduledVisit setInitialScheduledTime(OffsetDateTime initialScheduledTime) {
        this.initialScheduledTime = initialScheduledTime;
        return this;
    }

    public OffsetDateTime getEndScheduledTime() {
        return endScheduledTime;
    }

    public ScheduledVisit setEndScheduledTime(OffsetDateTime endScheduledTime) {
        this.endScheduledTime = endScheduledTime;
        return this;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public ScheduledVisit setCompleted(Boolean completed) {
        isCompleted = completed;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScheduledVisit setDescription(String description) {
        this.description = description;
        return this;
    }
}
