package com.faculdade.passig_empilhadeiras.repositories;

import com.faculdade.passig_empilhadeiras.models.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
    Parametro findByNome(String nome);
}
