package com.faculdade.passig_empilhadeiras.models;

import com.faculdade.passig_empilhadeiras.enums.ForkliftStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Forklift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Size(max = 50)
    @NotNull
    @Column(name = "manufacturer", nullable = false, length = 50)
    private String manufacturer;

    @NotNull
    @Column(name = "weigth_capacity_kg", nullable = false)
    private Float weigthCapacityKg;

    @NotNull
    @Column(name = "fabrication_year", nullable = false)
    private Integer fabricationYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 2)
    private ForkliftStatus status;

    @Column(name = "aquisition_date")
    private LocalDate aquisitionDate;

    @OneToMany(mappedBy = "forklift", fetch = FetchType.LAZY)
    private List<ForkliftImage> forkliftImages;

    public LocalDate getAquisitionDate() {
        return aquisitionDate;
    }

    public void setAquisitionDate(LocalDate aquisitionDate) {
        this.aquisitionDate = aquisitionDate;
    }

    public ForkliftStatus getStatus() {
        return status;
    }

    public void setStatus(ForkliftStatus status) {
        this.status = status;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public Float getWeigthCapacityKg() {
        return weigthCapacityKg;
    }

    public void setWeigthCapacityKg(Float weigthCapacityKg) {
        this.weigthCapacityKg = weigthCapacityKg;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ForkliftImage> getForkliftImages() {
        return forkliftImages;
    }

    public void setForkliftImages(List<ForkliftImage> forkliftImages) {
        this.forkliftImages = forkliftImages;
    }
}
