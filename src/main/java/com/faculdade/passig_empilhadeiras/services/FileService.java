package com.faculdade.passig_empilhadeiras.services;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@Component
public class FileService {

    public String handleFileUpload(MultipartFile file, String subDir) {

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads");
        uploadDir = subDir != null ? uploadDir.resolve(subDir) : uploadDir;

        File dir = uploadDir.toFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            Path filePath = uploadDir.resolve(originalFilename).normalize();
            file.transferTo(filePath.toFile());

            Path relativePath = Paths.get("uploads").resolve(subDir != null ? subDir : "").resolve(originalFilename).normalize();
            return relativePath.toString().replace(File.separatorChar, '/');
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao salvar o arquivo: " + e.getMessage();
        }
    }

    public String encodeToBase64(String fileDir){

        try{
            Path filePath = Paths.get(System.getProperty("user.dir"), fileDir);

            byte[] fileBytes = Files.readAllBytes(filePath);

            return Base64.getEncoder().encodeToString(fileBytes);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

