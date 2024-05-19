package com.example.demo.repositories;

import com.example.demo.entities.TypeOfFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITypeOfFoodRepository extends JpaRepository<TypeOfFood, Long> {

}
