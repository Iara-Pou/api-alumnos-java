package com.example.alumnosApi.controllers;

import com.example.alumnosApi.DTO.JWTAutenticacionRespuesta;
import com.example.alumnosApi.DTO.SignInRequest;
import com.example.alumnosApi.DTO.SignUpRequest;
import com.example.alumnosApi.services.ServiceAutenticacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {
    private final ServiceAutenticacion authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JWTAutenticacionRespuesta> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAutenticacionRespuesta> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}