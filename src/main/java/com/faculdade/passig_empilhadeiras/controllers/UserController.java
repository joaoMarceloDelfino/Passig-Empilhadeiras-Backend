package com.faculdade.passig_empilhadeiras.controllers;

import com.faculdade.passig_empilhadeiras.dtos.AuthDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.LoginDTOV1;
import com.faculdade.passig_empilhadeiras.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

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
}
