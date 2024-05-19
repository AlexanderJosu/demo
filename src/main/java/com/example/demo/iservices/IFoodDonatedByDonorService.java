package com.example.demo.iservices;



import com.example.demo.entities.FoodDonatedByDonor;

import java.util.List;

public interface IFoodDonatedByDonorService {
    //save
    public FoodDonatedByDonor save(FoodDonatedByDonor foodDonatedByDonor);
    //findAll
    List<FoodDonatedByDonor> findAll();
    //list by id
    FoodDonatedByDonor findFirstByUser(Long userId);
    //update
    public FoodDonatedByDonor update(FoodDonatedByDonor foodDonatedByDonor);
    //delete
    public void delete(Long id);

}
