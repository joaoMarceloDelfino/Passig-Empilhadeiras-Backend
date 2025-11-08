package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.dtos.ForkiliftDtoV1;
import com.faculdade.passig_empilhadeiras.enums.ForkliftStatus;
import com.faculdade.passig_empilhadeiras.mappers.ForkliftMapper;
import com.faculdade.passig_empilhadeiras.models.Forklift;
import com.faculdade.passig_empilhadeiras.models.ForkliftImage;
import com.faculdade.passig_empilhadeiras.repositories.ForkliftImageService;
import com.faculdade.passig_empilhadeiras.repositories.ForkliftRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForkliftService {

    @Autowired
    private ForkliftRepository forkliftRepository;
    @Resource
    private ForkliftMapper forkliftMapper;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ForkliftImageService forkliftImageService;

    public List<ForkiliftDtoV1> findAll(){
        return forkliftRepository
                .findAll()
                .stream()
                .map(forkliftMapper::convertToDto)
                .toList();
    }

    public Boolean save(String name,
                        String model,
                        String manufacturer,
                        Float weigthCapacityKg,
                        Integer fabricationYear,
                        ForkliftStatus status,
                        LocalDate aquisitionDate,
                        MultipartFile file){
        ForkiliftDtoV1 dto = new ForkiliftDtoV1();

        dto.setName(name).
         setModel(model).
         setManufacturer(manufacturer).
         setWeigthCapacityKg(weigthCapacityKg).
         setFabricationYear(fabricationYear).
         setStatus(status).
         setAquisitionDate(aquisitionDate);

        Forklift savedModel = forkliftRepository.saveAndFlush(forkliftMapper.convertToModel(dto));

        if(file != null){
            String fileAbsolutePath = fileUploadService.handleFileUpload(file);
            ForkliftImage forkliftImage = new ForkliftImage();
            forkliftImage.setForklift(savedModel);
            forkliftImage.setFileUrl(fileAbsolutePath);
            forkliftImageService.save(forkliftImage);
        }
        return true;
    }
}
