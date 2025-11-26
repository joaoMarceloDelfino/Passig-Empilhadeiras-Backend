package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.models.Parametro;
import com.faculdade.passig_empilhadeiras.repositories.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametroService {
    @Autowired
    private ParametroRepository parametroRepository;

    public String findValueByName(String name, String nullValue ){
        Parametro parametro = parametroRepository.findByNome(name);

        return parametro != null ? parametro.getValor() : nullValue;
    }
}
