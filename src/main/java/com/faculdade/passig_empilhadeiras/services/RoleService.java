package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.models.Role;
import com.faculdade.passig_empilhadeiras.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
