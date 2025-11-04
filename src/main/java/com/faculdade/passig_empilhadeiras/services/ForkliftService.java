package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.dtos.ForkiliftDtoV1;
import com.faculdade.passig_empilhadeiras.mappers.ForkliftMapper;
import com.faculdade.passig_empilhadeiras.repositories.ForkliftRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForkliftService {

    @Autowired
    private ForkliftRepository forkliftRepository;
    @Resource
    private ForkliftMapper forkliftMapper;

    public List<ForkiliftDtoV1> findAll(){
        return forkliftRepository
                .findAll()
                .stream()
                .map(forkliftMapper::convertToDto)
                .toList();
    }

    public Boolean save(ForkiliftDtoV1 dto){
        forkliftRepository.saveAndFlush(forkliftMapper.convertToModel(dto));
        return true;
    }
}
