package com.faculdade.passig_empilhadeiras.mappers;

import com.faculdade.passig_empilhadeiras.dtos.ForkiliftDtoV1;
import com.faculdade.passig_empilhadeiras.models.Forklift;
import org.springframework.stereotype.Component;

@Component
public class ForkliftMapper {

    public ForkiliftDtoV1 convertToDto(Forklift model) {
        ForkiliftDtoV1 dto = new ForkiliftDtoV1();
        dto.setId(model.getId());
        dto.setAquisitionDate(model.getAquisitionDate());
        dto.setModel(model.getModel());
        dto.setManufacturer(model.getManufacturer());
        dto.setName(model.getName());
        dto.setStatus(model.getStatus());
        dto.setWeigthCapacityKg(model.getWeigthCapacityKg());
        dto.setFabricationYear(model.getFabricationYear());

        return dto;
    }

    public Forklift convertToModel(ForkiliftDtoV1 dto) {
        Forklift model = new Forklift();
        model.setId(dto.getId());
        model.setAquisitionDate(dto.getAquisitionDate());
        model.setModel(dto.getModel());
        model.setManufacturer(dto.getManufacturer());
        model.setName(dto.getName());
        model.setStatus(dto.getStatus());
        model.setWeigthCapacityKg(dto.getWeigthCapacityKg());
        model.setFabricationYear(dto.getFabricationYear());

        return model;
    }
}
