package com.example.demo.iservices;



import com.example.demo.entities.TypeOfFood;

import java.util.List;
import java.util.Optional;

public interface ITypeOfFoodService {
    //save
    public TypeOfFood save(TypeOfFood typeOfFood);

    //delete
    public void delete(Long id);

    //update
    public TypeOfFood update(TypeOfFood typeOfFood);

    //findall
    public List<TypeOfFood> findAll();
    Optional<TypeOfFood> findById(Long id);
}
