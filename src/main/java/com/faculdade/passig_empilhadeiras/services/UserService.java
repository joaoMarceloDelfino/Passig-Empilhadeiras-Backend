package com.faculdade.passig_empilhadeiras.services;

import com.faculdade.passig_empilhadeiras.config.JwtTokenService;
import com.faculdade.passig_empilhadeiras.config.UserDetailsImpl;
import com.faculdade.passig_empilhadeiras.dtos.AuthDTOV1;
import com.faculdade.passig_empilhadeiras.dtos.LoginDTOV1;
import com.faculdade.passig_empilhadeiras.models.User;
import com.faculdade.passig_empilhadeiras.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;



    public String login(LoginDTOV1 loginDTOV1, @CookieValue(value = "accessToken", required = false) String accessToken, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTOV1.getEmail(),
                        loginDTOV1.getPassword()
                )
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails.getUsername());
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        String refreshToken = jwtTokenService.generateRefreshToken(userDetails.getUsername());
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setMaxAge(604800);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return token;
    }

    @Transactional
    public Boolean register(AuthDTOV1 authDTOV1) {

        if(userRepository.existsByEmail(authDTOV1.getEmail())) {
            throw new RuntimeException("Usuario com este nome já existe!");
        }

        if(!authDTOV1.getPassword().equals(authDTOV1.getPasswordConfirmation())){
            throw new RuntimeException("Senha deve ser igual a confirmação de senha");
        }

        User user = new User();
        user.setUsername(authDTOV1.getUsername());
        user.setEmail(authDTOV1.getEmail());
        user.setCellphoneNumber(authDTOV1.getCellphoneNumber());
        user.setPasswordHash(BCrypt.hashpw(authDTOV1.getPassword(), BCrypt.gensalt()));
        user.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC).toOffsetDateTime());
        userRepository.saveAndFlush(user);

        return true;
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User getLoggedUser(){
        String userLoggedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userLoggedEmail);
    }

    public Boolean isUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().
                getAuthentication();

        return authentication != null &&
                authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public Boolean logout(HttpServletResponse response){
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        Cookie refreshCookie = new Cookie("refreshToken", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0);

        response.addCookie(cookie);
        response.addCookie(refreshCookie);

        return true;
    }

    public String generateRefreshTokenByAccessToken(String refreshToken, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenService.extractUsername(refreshToken));

        if(!jwtTokenService.validateToken(refreshToken, userDetails)){
            throw new RuntimeException("Refresh Token inválido");
        }

        String token = jwtTokenService.generateToken(userDetails.getUsername());
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return token;
    }

}
