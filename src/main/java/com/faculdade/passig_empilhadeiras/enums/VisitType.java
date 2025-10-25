package com.faculdade.passig_empilhadeiras.enums;

public enum VisitType {

    AL("Aluguel de empilhadeiras"),
    MA("Manutenção de empilhadeiras");

    private final String description;

    VisitType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
