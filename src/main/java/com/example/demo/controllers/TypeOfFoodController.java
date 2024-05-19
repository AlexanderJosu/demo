package com.example.demo.controllers;

import com.example.demo.dtos.TypeOfFoodDTO;
import com.example.demo.entities.TypeOfFood;
import com.example.demo.iservices.ITypeOfFoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TypeOfFoodController {
    @Autowired
    private ITypeOfFoodService iTypeOfFoodService;

    @PostMapping("/TypeOfFood/save") // http://localhost:8080/api/TypeOfFood/save
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can save
    public ResponseEntity<?> save(@RequestBody TypeOfFoodDTO typeOfFoodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TypeOfFood typeOfFood = modelMapper.map(typeOfFoodDTO, TypeOfFood.class);
            typeOfFood = iTypeOfFoodService.save(typeOfFood);
            typeOfFoodDTO = modelMapper.map(typeOfFood, TypeOfFoodDTO.class);
            return new ResponseEntity<>(typeOfFoodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    @DeleteMapping("/TypeOfFood/delete/{id}") // http://localhost:8080/api/TypeOfFood/delete/1
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iTypeOfFoodService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

    @PutMapping("/TypeOfFood/update") // http://localhost:8080/api/TypeOfFood/update
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can update
    public ResponseEntity<?> update(@RequestBody TypeOfFoodDTO typeOfFoodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TypeOfFood typeOfFood = modelMapper.map(typeOfFoodDTO, TypeOfFood.class);
            typeOfFood = iTypeOfFoodService.update(typeOfFood);
            typeOfFoodDTO = modelMapper.map(typeOfFood, TypeOfFoodDTO.class);
            return new ResponseEntity<>(typeOfFoodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    @GetMapping("/TypeOfFood/findAll") // http://localhost:8080/api/TypeOfFood/findAll
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can find all
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iTypeOfFoodService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de alimento");
        }
    }


    @GetMapping("/TypeOfFood/find/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can find all
    public ResponseEntity<TypeOfFood> getTypeOfFoodById(@PathVariable Long id) {
        Optional<TypeOfFood> typeOfFood = iTypeOfFoodService.findById(id);
        if (typeOfFood.isPresent()) {
            return new ResponseEntity<>(typeOfFood.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
