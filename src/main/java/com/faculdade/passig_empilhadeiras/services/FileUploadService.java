package com.faculdade.passig_empilhadeiras.services;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class FileUploadService {

    public String handleFileUpload(MultipartFile file) {

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
        File dir = uploadDir.toFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            Path filePath = uploadDir.resolve(originalFilename).normalize();
            file.transferTo(filePath.toFile());
            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao salvar o arquivo: " + e.getMessage();
        }
    }
}

