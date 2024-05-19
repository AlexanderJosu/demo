package com.example.demo.controllers;

import com.example.demo.dtos.FoodDonationDTO;
import com.example.demo.entities.FoodDonation;
import com.example.demo.iservices.IFoodDonationService;
import com.example.demo.iservices.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class FoodDonationController {
    @Autowired
    private IFoodDonationService iFoodDonationService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/FoodDonation/save") //localhost:8080/api/FoodDonation/save
    @PreAuthorize("hasAuthority('DONANTE')") //only DONANTE can save
         public ResponseEntity<?> save(@RequestBody FoodDonationDTO foodDonationDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonation foodDonation = modelMapper.map(foodDonationDTO, FoodDonation.class);
            foodDonation = iFoodDonationService.save(foodDonation);
            foodDonationDTO = modelMapper.map(foodDonation, FoodDonationDTO.class);
            return new ResponseEntity<>(foodDonationDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    @GetMapping("/FoodDonation/findAll") //localhost:8080/api/FoodDonation/findAll
    @PreAuthorize("hasAuthority('DONANTE') or hasAnyAuthority('ADMIN')" ) //only admin can see all
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iFoodDonationService.findall(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de alimentos");
        }
    }

    @PutMapping("/FoodDonation/update") //localhost:8080/api/FoodDonation/update
    @PreAuthorize("hasAuthority('DONANTE')") //only admin can update
    public ResponseEntity<?> update(@RequestBody FoodDonationDTO foodDonationDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonation foodDonation = modelMapper.map(foodDonationDTO, FoodDonation.class);
            foodDonation = iFoodDonationService.update(foodDonation);
            foodDonationDTO = modelMapper.map(foodDonation, FoodDonationDTO.class);
            return new ResponseEntity<>(foodDonationDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    @DeleteMapping("/FoodDonation/delete/{id}") //localhost:8080/api/FoodDonation/delete/1
    @PreAuthorize("hasAuthority('DONANTE')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iFoodDonationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

}
