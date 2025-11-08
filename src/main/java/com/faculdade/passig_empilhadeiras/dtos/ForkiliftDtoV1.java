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

    public ForkiliftDtoV1 setStatus(ForkliftStatus status) {
        this.status = status;
        return this;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public ForkiliftDtoV1 setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
        return this;
    }

    public Float getWeigthCapacityKg() {
        return weigthCapacityKg;
    }

    public ForkiliftDtoV1 setWeigthCapacityKg(Float weigthCapacityKg) {
        this.weigthCapacityKg = weigthCapacityKg;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ForkiliftDtoV1 setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ForkiliftDtoV1 setModel(String model) {
        this.model = model;
        return this;
    }

    public String getName() {
        return name;
    }

    public ForkiliftDtoV1 setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ForkiliftDtoV1 setId(Integer id) {
        this.id = id;
        return this;
    }

}
