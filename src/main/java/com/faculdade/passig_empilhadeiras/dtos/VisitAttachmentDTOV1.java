package com.faculdade.passig_empilhadeiras.dtos;

import com.faculdade.passig_empilhadeiras.models.ScheduledVisit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VisitAttachmentDTOV1 {

    private Integer id;
    private String base64Url;
    private String extension;

    public String getBase64Url() {
        return base64Url;
    }

    public void setBase64Url(String base64Url) {
        this.base64Url = base64Url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
