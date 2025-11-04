package com.faculdade.passig_empilhadeiras.controllers;

import com.faculdade.passig_empilhadeiras.dtos.ForkiliftDtoV1;
import com.faculdade.passig_empilhadeiras.services.ForkliftService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/forklifts")
public class ForkliftController {

    @Autowired
    private ForkliftService forkliftService;
    Logger logger = LoggerFactory.getLogger(ForkliftService.class);

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<ForkiliftDtoV1>> findAll(){
        logger.info("Start find All Empilhadeiras");
        List<ForkiliftDtoV1> forklifts = forkliftService.findAll();

        if(forklifts == null || forklifts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        logger.info("End find All Empilhadeiras");

        return ResponseEntity.ok(forklifts);
    }

    @PostMapping(value = "/save")
    public Boolean save(@RequestBody @Valid ForkiliftDtoV1 dto){
        logger.info("Start save");
        Boolean retorno = forkliftService.save(dto);
        logger.info("End save");
        return retorno;
    }
}
