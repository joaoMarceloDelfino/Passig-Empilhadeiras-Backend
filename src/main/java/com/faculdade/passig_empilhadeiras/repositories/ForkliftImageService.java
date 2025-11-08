package com.faculdade.passig_empilhadeiras.repositories;

import com.faculdade.passig_empilhadeiras.models.ForkliftImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForkliftImageService {
    @Autowired
    private ForkliftImageRepository forkliftImageRepository;
    public void save(ForkliftImage forkliftImage) {
        forkliftImageRepository.save(forkliftImage);
    }
}
