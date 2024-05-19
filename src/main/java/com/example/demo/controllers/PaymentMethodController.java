package com.example.demo.controllers;

import com.example.demo.dtos.PaymentMethodDTO;
import com.example.demo.entities.PaymentMethod;
import com.example.demo.iservices.IPaymentMethodService;
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
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService iPaymentMethodService;

    @PostMapping("/PaymentMethod/save") //localhost:8080/api/PaymentMethod/save
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
    public ResponseEntity<?> save(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            PaymentMethod paymentMethod = modelMapper.map(paymentMethodDTO, PaymentMethod.class);
            paymentMethod = iPaymentMethodService.save(paymentMethod);
            paymentMethodDTO = modelMapper.map(paymentMethod, PaymentMethodDTO.class);
            return new ResponseEntity<>(paymentMethodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    @GetMapping("/PaymentMethod/findAll")
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iPaymentMethodService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al obtener donaciones de alimento");
        }
    }

    @DeleteMapping("/PaymentMethod/delete/{id}") //localhost:8080/api/PaymentMethod/delete/1
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iPaymentMethodService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

    @PutMapping("/PaymentMethod/update") //localhost:8080/api/PaymentMethod/update
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
    public ResponseEntity<?> update(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            PaymentMethod paymentMethod = modelMapper.map(paymentMethodDTO, PaymentMethod.class);
            paymentMethod = iPaymentMethodService.update(paymentMethod);
            paymentMethodDTO = modelMapper.map(paymentMethod, PaymentMethodDTO.class);
            return new ResponseEntity<>(paymentMethodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

}
