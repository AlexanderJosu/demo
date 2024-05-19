package com.example.demo.controllers;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.iservices.IUserService;
import com.example.demo.util.DTOConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private DTOConverter dtoConverter;

    // SOLO LOS USUARIOS CON ROL ADMIN PUEDEN ACCEDER A ESTOS ENDPOINTS
    @PreAuthorize("hasAuthority('ADMIN')")
    //para visualizar todos los usuarios
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> list() {
        List<User> users = userService.list();
        List<UserDTO> userDTOs = users.stream().map(user -> dtoConverter.convertToDto(user, UserDTO.class)).toList();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    //para visualizar todos los usuarios con rol donante
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/donante")
    public ResponseEntity<List<UserDTO>> listDonante() {
        List<User> users = userService.findByRoleDonante();
        List<UserDTO> userDTOs = users.stream().map(user -> dtoConverter.convertToDto(user, UserDTO.class)).toList();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DONANTE') or hasAuthority('ADMIN')")
    @PutMapping("/user")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) throws Exception {
        User user = dtoConverter.convertToEntity(userDTO, User.class);
        user = userService.update(user);
        userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        userService.delete(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
