package com.faculdade.passig_empilhadeiras.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class VisitAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "file_url", nullable = false, length = 200)
    private String fileUrl;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_scheduled_visit", nullable = false)
    private ScheduledVisit idScheduledVisit;

    public ScheduledVisit getIdScheduledVisit() {
        return idScheduledVisit;
    }

    public void setIdScheduledVisit(ScheduledVisit idScheduledVisit) {
        this.idScheduledVisit = idScheduledVisit;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
