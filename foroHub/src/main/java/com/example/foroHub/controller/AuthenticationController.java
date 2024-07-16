package com.example.foroHub.controller;

import com.example.foroHub.domain.user.User;
import com.example.foroHub.domain.user.UserAuthenticationData;
import com.example.foroHub.infra.security.JWTTokenData;
import com.example.foroHub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Obtiene el token para el usuario asignado que da acceso al resto del endpoint")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserAuthenticationData userAuthenticationData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(), userAuthenticationData.password());
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);
        String jwtToken = tokenService.generarToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(jwtToken));
    }
}
