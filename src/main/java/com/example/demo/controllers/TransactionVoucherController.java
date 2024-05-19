package com.example.demo.controllers;

import com.example.demo.dtos.TransactionVoucherDTO;
import com.example.demo.entities.TransactionVoucher;
import com.example.demo.entities.User;
import com.example.demo.iservices.ITransactionVoucherService;
import com.example.demo.iservices.IUserService;
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
public class TransactionVoucherController {
    @Autowired
    private ITransactionVoucherService iTransactionVoucherService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/TransactionVoucher/save") //localhost:8080/api/TransactionVoucher/save
    @PreAuthorize("hasAuthority('DONANTE')") //only donor can save transaction voucher
    public ResponseEntity<?> save(@RequestBody TransactionVoucherDTO transactionVoucherDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TransactionVoucher transactionVoucher = modelMapper.map(transactionVoucherDTO, TransactionVoucher.class);
            transactionVoucher = iTransactionVoucherService.save(transactionVoucher);
            transactionVoucherDTO = modelMapper.map(transactionVoucher, TransactionVoucherDTO.class);
            return new ResponseEntity<>(transactionVoucherDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    //findall
    @GetMapping("/TransactionVoucher/findAll") //localhost:8080/api/TransactionVoucher/findAll
    @PreAuthorize("hasAuthority('DONANTE') or hasAuthority('ADMIN') ") //only admin can see all transaction vouchers
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iTransactionVoucherService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de alimentos");
        }
    }

    //update
    @PutMapping("/TransactionVoucher/update") //localhost:8080/api/TransactionVoucher/update
    @PreAuthorize("hasAuthority('DONANTE')") //only donor can update transaction voucher
    public ResponseEntity<?> update(@RequestBody TransactionVoucherDTO transactionVoucherDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TransactionVoucher transactionVoucher = modelMapper.map(transactionVoucherDTO, TransactionVoucher.class);
            transactionVoucher = iTransactionVoucherService.update(transactionVoucher);
            transactionVoucherDTO = modelMapper.map(transactionVoucher, TransactionVoucherDTO.class);
            return new ResponseEntity<>(transactionVoucherDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    //delete
    @DeleteMapping("/TransactionVoucher/delete/{id}") //localhost:8080/api/TransactionVoucher/delete/1
    @PreAuthorize("hasAuthority('DONANTE')") //only donor can delete transaction voucher
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iTransactionVoucherService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }


    @GetMapping("/TransactionVoucher/firstByUser/{userId}") //localhost:8080/api/TransactionVoucher/firstByUser/1
    @PreAuthorize("hasAuthority('ADMIN') ") //only donor or admin can see the first transaction voucher by user
    public ResponseEntity<?> findFirstByUser(@PathVariable Long userId) {
        try{
            Optional<User> user = iUserService.findById(userId);
            Optional<TransactionVoucher> transactionVoucher = iTransactionVoucherService.findFirstByUser(user);
            if(transactionVoucher.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                TransactionVoucherDTO transactionVoucherDTO = modelMapper.map(transactionVoucher.get(), TransactionVoucherDTO.class);
                return new ResponseEntity<>(transactionVoucherDTO, HttpStatus.OK);
            } else {
                // Mensaje personalizado para el frontend
                return new ResponseEntity<>("No se encontró ninguna donación de alimento para el usuario con id: " + userId, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            // Mensaje personalizado para el backend
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar la primera donación de alimento para el usuario con id: " + userId, e);
        }
    }

}
