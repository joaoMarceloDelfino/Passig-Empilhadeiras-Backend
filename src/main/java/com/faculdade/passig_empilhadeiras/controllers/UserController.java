package com.faculdade.passig_empilhadeiras.controllers;

import com.faculdade.passig_empilhadeiras.dtos.AuthDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.LoginDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.UserDTOV1;
import com.faculdade.passig_empilhadeiras.mappers.UserMapper;
import com.faculdade.passig_empilhadeiras.services.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserMapper  userMapper;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTOV1 loginDTOV1, String token, HttpServletResponse response){
        logger.info("Start login");
        String accessToken = userService.login(loginDTOV1, token, response);
        logger.info("End login");
        return accessToken;
    }

    @PostMapping("/register")
    public Boolean register(@RequestBody AuthDTOV1 authDTOV1){
        logger.info("Start register");
        Boolean isRegistered = userService.register(authDTOV1);
        logger.info("End register");
        return isRegistered;
    }

    @GetMapping("/existsByEmail")
    public Boolean existsByEmail(String email){
        logger.info("Start existByEmail");
        Boolean userExists = userService.existsByEmail(email);
        logger.info("End existsByEmail");
        return userExists;
    }

    @GetMapping("/isUserLogged")
    public Boolean isUserLogged(){
        logger.info("Start isUserLogged");
        Boolean userLogged = userService.isUserLogged();
        logger.info("End isUserLogged");
        return userLogged;
    }

    @PostMapping("/logout")
    public Boolean logout(HttpServletResponse response) {
        logger.info("Start logout");
        Boolean logout = userService.logout(response);
        logger.info("End logout");
        return logout;
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshAccessToken(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        try{
            String accessToken = userService.generateRefreshTokenByAccessToken(refreshToken, response);
            return ResponseEntity.ok(accessToken);
        }
        catch(Exception ex){
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/getLoggedUser")
    public UserDTOV1 getLoggedUser(){
        logger.info("Start getLoggedUser");
        UserDTOV1 user = userMapper.convertToDTO(userService.getLoggedUser());
        logger.info("End getLoggedUser");
        return user;
    }


}
