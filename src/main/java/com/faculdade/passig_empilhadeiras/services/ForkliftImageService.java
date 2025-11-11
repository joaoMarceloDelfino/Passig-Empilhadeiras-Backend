package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.models.ForkliftImage;
import com.faculdade.passig_empilhadeiras.repositories.ForkliftImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForkliftImageService {
    @Autowired
    private ForkliftImageRepository forkliftImageRepository;
    public void save(ForkliftImage forkliftImage) {
        forkliftImageRepository.save(forkliftImage);
    }

    public List<ForkliftImage>findAllByForkliftId(Integer forkliftId){
        return forkliftImageRepository.findAllByForkliftId(forkliftId);
    }
}
