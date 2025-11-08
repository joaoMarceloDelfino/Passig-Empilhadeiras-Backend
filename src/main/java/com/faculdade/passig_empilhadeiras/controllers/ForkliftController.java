package com.faculdade.passig_empilhadeiras.controllers;

import com.faculdade.passig_empilhadeiras.dtos.ForkiliftDtoV1;
import com.faculdade.passig_empilhadeiras.enums.ForkliftStatus;
import com.faculdade.passig_empilhadeiras.services.ForkliftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    public Boolean save(@RequestParam List<MultipartFile> file,
                        @RequestParam String name,
                        @RequestParam String model,
                        @RequestParam String manufacturer,
                        @RequestParam Float weigthCapacityKg,
                        @RequestParam Integer fabricationYear,
                        @RequestParam ForkliftStatus status,
                        @RequestParam LocalDate aquisitionDate){
        logger.info("Start save");
        Boolean retorno = forkliftService.save(name, model, manufacturer, weigthCapacityKg, fabricationYear, status, aquisitionDate, file);
        logger.info("End save");
        return retorno;
    }
}
