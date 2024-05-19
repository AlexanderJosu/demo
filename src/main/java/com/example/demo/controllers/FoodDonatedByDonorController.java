package com.example.demo.controllers;

import com.example.demo.dtos.FoodDonatedByDonorDTO;
import com.example.demo.entities.FoodDonatedByDonor;
import com.example.demo.entities.User;
import com.example.demo.iservices.IFoodDonatedByDonorService;
import com.example.demo.iservices.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FoodDonatedByDonorController {
    @Autowired
    private IFoodDonatedByDonorService iFoodDonatedByDonorService;

    @Autowired
private IUserService iUserService;

    //save donor food
    @PostMapping("/FoodDonatedByDonor/save") //localhost:8080/api/FoodDonatedByDonor/save
    @PreAuthorize("hasAuthority('DONANTE')") //only donor
    public ResponseEntity<?> save(@RequestBody FoodDonatedByDonorDTO foodDonatedByDonorDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonatedByDonor foodDonatedByDonor = modelMapper.map(foodDonatedByDonorDTO, FoodDonatedByDonor.class);
            foodDonatedByDonor = iFoodDonatedByDonorService.save(foodDonatedByDonor);
            foodDonatedByDonorDTO = modelMapper.map(foodDonatedByDonor, FoodDonatedByDonorDTO.class);
            return new ResponseEntity<>(foodDonatedByDonorDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    //listar todos
    @GetMapping("/FoodDonatedByDonor/findAll") //localhost:8080/api/FoodDonatedByDonor/findAll
    @PreAuthorize("hasAuthority('DONANTE') or hasAuthority('ADMIN')") //only donor and admin
    public ResponseEntity<?> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<FoodDonatedByDonorDTO> list = Arrays.asList(modelMapper.map(iFoodDonatedByDonorService.findAll(),
                FoodDonatedByDonorDTO[].class));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //listar por donor id
    @GetMapping("/FoodDonatedByDonor/findFirstByUser/{userId}") //localhost:8080/api/FoodDonatedByDonor/findFirstByUser/{userId}
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
    public ResponseEntity<?> findFirstByUser(@PathVariable Long userId) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> user = iUserService.findDonorById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity<>("Usuario no encontrado o no es un donante", HttpStatus.NOT_FOUND);
        }

        FoodDonatedByDonorDTO foodDonatedByDonorDTO = modelMapper.map(iFoodDonatedByDonorService.findFirstByUser(userId),
                FoodDonatedByDonorDTO.class);
        return new ResponseEntity<>(foodDonatedByDonorDTO, HttpStatus.OK);
    }

    //actualizar
    @PutMapping("/FoodDonatedByDonor/update") //localhost:8080/api/FoodDonatedByDonor/update
    @PreAuthorize("hasAuthority('DONANTE')") //only donor
    public ResponseEntity<?> update(@RequestBody FoodDonatedByDonorDTO foodDonatedByDonorDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonatedByDonor foodDonatedByDonor = modelMapper.map(foodDonatedByDonorDTO, FoodDonatedByDonor.class);
            foodDonatedByDonor = iFoodDonatedByDonorService.update(foodDonatedByDonor);
            foodDonatedByDonorDTO = modelMapper.map(foodDonatedByDonor, FoodDonatedByDonorDTO.class);
            return new ResponseEntity<>(foodDonatedByDonorDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }


    //elimnar
    @DeleteMapping("/FoodDonatedByDonor/delete/{food_donated_by_donor_id}") //localhost:8080/api/FoodDonatedByDonor/delete/1
    @PreAuthorize("hasAuthority('DONANTE')") //only donor
    public ResponseEntity<?> delete(@PathVariable("food_donated_by_donor_id") Long id) {
        try {
            iFoodDonatedByDonorService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }
}
