package com.example.demo.controllers;

import com.example.demo.iservices.ITransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransactionDetailController {
    @Autowired
    private ITransactionDetailService iTransactionDetailService;

    @DeleteMapping("/TransactionDetail/delete/{id}") //localhost:8080/api/TransactionDetail/delete/1
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iTransactionDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

    @GetMapping("/TransactionDetail/getAll") //localhost:8080/api/TransactionDetail/getAll
    @PreAuthorize("hasAuthority('ADMIN')") //only admin can get all
    public ResponseEntity<?> getAll() {
        try{
            return new ResponseEntity<>(iTransactionDetailService.getAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al obtener donaciones de alimentos");
        }
    }


}
