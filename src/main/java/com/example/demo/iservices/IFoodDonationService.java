package com.example.demo.iservices;



import com.example.demo.entities.FoodDonation;

import java.util.List;

public interface IFoodDonationService {
    //save
    public FoodDonation save(FoodDonation foodDonation);

    //delete
    public void delete(Long id);

    //findall
    public List<FoodDonation> findall();

    //update
    public FoodDonation update(FoodDonation foodDonation);


}
