package com.example.alumnosApi.services;

import com.example.alumnosApi.DTO.JWTAutenticacionRespuesta;
import com.example.alumnosApi.DTO.SignInRequest;
import com.example.alumnosApi.DTO.SignUpRequest;
import com.example.alumnosApi.entities.Alumno;
import com.example.alumnosApi.repositories.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAutenticacion {

    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JWTAutenticacionRespuesta signin(SignInRequest request) {
        authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var alumno = alumnoRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(alumno);
        return JWTAutenticacionRespuesta.builder().token(jwt).build();

    }

    public JWTAutenticacionRespuesta signup(SignUpRequest request) {
        var alumno = Alumno.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .abonoMatricula(request.isAbonoMatricula())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol())
                .build();

        alumnoRepository.save(alumno);
        var jwt = jwtService.generateToken(alumno);
        return JWTAutenticacionRespuesta.builder().token(jwt).build();
    }

}
