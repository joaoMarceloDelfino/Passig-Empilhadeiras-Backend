package com.faculdade.passig_empilhadeiras.dtos;

import com.faculdade.passig_empilhadeiras.enums.ForkliftStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ForkiliftDtoV1 {

    private Integer id;

    @Size(max = 50)
    @NotNull
    private String name;

    @Size(max = 50)
    @NotNull
    private String model;

    @Size(max = 50)
    @NotNull
    private String manufacturer;

    @NotNull
    private Float weigthCapacityKg;

    @NotNull
    private Integer fabricationYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ForkliftStatus status;

    private LocalDate aquisitionDate;


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

}
