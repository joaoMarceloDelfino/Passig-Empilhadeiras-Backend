package com.faculdade.passig_empilhadeiras.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
public class ForkliftRentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_forklift", nullable = false)
    private Forklift forklift;

    @NotNull
    @Column(name = "start_rent_date", nullable = false)
    private OffsetDateTime startRentDate;

    @Column(name = "end_rent_date")
    private OffsetDateTime endRentDate;

    public OffsetDateTime getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(OffsetDateTime endRentDate) {
        this.endRentDate = endRentDate;
    }

    public OffsetDateTime getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(OffsetDateTime startRentDate) {
        this.startRentDate = startRentDate;
    }

    public Forklift getForklift() {
        return forklift;
    }

    public void setForklift(Forklift forklift) {
        this.forklift = forklift;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
