package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.dtos.ForkiliftDtoV1;
import com.faculdade.passig_empilhadeiras.dtos.ForkliftImageDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.ForkliftRentDTOV1;
import com.faculdade.passig_empilhadeiras.enums.ForkliftStatus;
import com.faculdade.passig_empilhadeiras.mappers.ForkliftMapper;
import com.faculdade.passig_empilhadeiras.models.Forklift;
import com.faculdade.passig_empilhadeiras.models.ForkliftImage;
import com.faculdade.passig_empilhadeiras.repositories.ForkliftRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForkliftService {

    @Autowired
    private ForkliftRepository forkliftRepository;
    @Resource
    private ForkliftMapper forkliftMapper;
    @Autowired
    FileService fileUploadService;
    @Autowired
    ForkliftImageService forkliftImageService;
    @Autowired
    FileService fileService;

    public List<ForkiliftDtoV1> findAll() {

        List<ForkiliftDtoV1> forkliftsDtoList = new ArrayList<>();
        List<Forklift> models = forkliftRepository.findAll();

        for (Forklift model : models) {
            ForkiliftDtoV1 dto = forkliftMapper.convertToDto(model);
            List<ForkliftImage> forkliftImages = forkliftImageService.findAllByForkliftId(model.getId());
            List<ForkliftImageDTOV1> forkliftImagesDtoList = new ArrayList<>();

            for (ForkliftImage forkliftImage : forkliftImages) {
                ForkliftImageDTOV1 forkliftImageDTO = new ForkliftImageDTOV1();
                String forkliftImageFileUrl = forkliftImage.getFileUrl();
                forkliftImageDTO.setExtension(forkliftImageFileUrl.substring(forkliftImageFileUrl.lastIndexOf(".")));
                forkliftImageDTO.setBase64Url(fileUploadService.encodeToBase64(forkliftImageFileUrl));
                forkliftImagesDtoList.add(forkliftImageDTO);
            }

            dto.setBase64Images(forkliftImagesDtoList);
            forkliftsDtoList.add(dto);
        }

        return forkliftsDtoList;
    }

    public Boolean save(String name,
            String model,
            String manufacturer,
            Float weigthCapacityKg,
            Integer fabricationYear,
            ForkliftStatus status,
            LocalDate aquisitionDate,
            List<MultipartFile> files) {

        ForkiliftDtoV1 dto = new ForkiliftDtoV1();

        dto.setName(name).setModel(model).setManufacturer(manufacturer).setWeigthCapacityKg(weigthCapacityKg)
                .setFabricationYear(fabricationYear).setStatus(status).setAquisitionDate(aquisitionDate);

        Forklift savedModel = forkliftRepository.saveAndFlush(forkliftMapper.convertToModel(dto));

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileAbsolutePath = fileUploadService.handleFileUpload(file, "forklift_images");
                ForkliftImage forkliftImage = new ForkliftImage();
                forkliftImage.setForklift(savedModel);
                forkliftImage.setFileUrl(fileAbsolutePath);
                forkliftImageService.save(forkliftImage);
            }
        }
        return true;
    }

    @Transactional
    public Boolean deleteById(Integer id) {

        Forklift forklift = forkliftRepository.findById(id).orElse(null);
        if (forklift == null) {
            return false;
        }

        List<ForkliftImage> images = forkliftImageService.findAllByForkliftId(id);

        for (ForkliftImage image : images) {
            String fileUrl = image.getFileUrl();
            fileService.deleteFile(fileUrl);
        }

        forkliftImageService.deleteAllByForkliftId(id);

        forkliftRepository.delete(forklift);

        return true;
    }

    public Boolean updateById(Integer id, String name,
            String model,
            String manufacturer,
            Float weigthCapacityKg,
            Integer fabricationYear,
            ForkliftStatus status,
            LocalDate aquisitionDate) {

        Forklift forklift = forkliftRepository.findById(id).orElse(null);
        if (forklift == null) {
            return false;
        }

        forklift.setName(name);
        forklift.setModel(model);
        forklift.setManufacturer(manufacturer);
        forklift.setWeigthCapacityKg(weigthCapacityKg);
        forklift.setFabricationYear(fabricationYear);
        forklift.setStatus(status);
        forklift.setAquisitionDate(aquisitionDate);

        forkliftRepository.save(forklift);

        return true;
    }

    public Forklift findById(Integer id) {
        return forkliftRepository.findById(id).orElse(null);
    }

}
