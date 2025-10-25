package com.faculdade.passig_empilhadeiras.enums;

public enum ForkliftStatus {
    FO("Funcionando"),
    NF("Não Funcionando"),
    MA("Manutenção"),
    IN("Inativa"),
    RE("Reservada");

    private final String description;

    ForkliftStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
