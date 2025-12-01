package com.faculdade.passig_empilhadeiras.mappers;

import com.faculdade.passig_empilhadeiras.dtos.UserDTOV1;
import com.faculdade.passig_empilhadeiras.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTOV1 convertToDTO(User user){
        UserDTOV1 userDTO = new UserDTOV1();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setCellphoneNumber(user.getCellphoneNumber());
        userDTO.setRole(user.getRole());
        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}
