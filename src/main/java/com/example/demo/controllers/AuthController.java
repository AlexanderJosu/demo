package com.example.demo.controllers;

import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.request.LoginUserRequestDTO;
import com.example.demo.dtos.request.RegisterUserRequestDTO;
import com.example.demo.dtos.response.JwtResponse;
import com.example.demo.entities.User;
import com.example.demo.iservices.IUserService;
import com.example.demo.util.DTOConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//Registrar y loguear usuarios
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private DTOConverter dtoConverter;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterUserRequestDTO request) {
        User user = dtoConverter.convertToEntity(request, User.class);
        user = userService.insert(user);
        UserDTO userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequestDTO request) {
        JwtResponse jwtResponse = userService.login(request.getEmail(), request.getPassword());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", jwtResponse.getJwttoken());
        return ResponseEntity.ok().headers(responseHeaders).body(jwtResponse);
    }
}
