package com.faculdade.passig_empilhadeiras.repositories;

import com.faculdade.passig_empilhadeiras.models.ForkliftImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForkliftImageRepository extends JpaRepository<ForkliftImage, Integer> {
    List<ForkliftImage> findAllByForkliftId(Integer forkliftId);
}
