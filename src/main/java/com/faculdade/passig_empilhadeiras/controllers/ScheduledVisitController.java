package com.faculdade.passig_empilhadeiras.controllers;

import com.faculdade.passig_empilhadeiras.services.ScheduledVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/scheduledVisit")
public class ScheduledVisitController {

    @Autowired
    ScheduledVisitService scheduledVisitService;
    Logger logger = LoggerFactory.getLogger(ScheduledVisitController.class);

    @PostMapping("/save")
    public Boolean saveScheduledVisit(@RequestParam OffsetDateTime initialScheduledTime,
                      @RequestParam OffsetDateTime endScheduledTime,
                      @RequestParam String description,
                      @RequestParam("file") List<MultipartFile> visitAttachments){
        logger.info("Start saveScheduledVisit");
        Boolean retorno = scheduledVisitService.saveScheduledVisit(initialScheduledTime, endScheduledTime, description, visitAttachments);
        logger.info("End saveScheduledVisit");

        return retorno;
    }
}
