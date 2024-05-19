package com.example.demo.iservices.services;

import com.example.demo.entities.FoodDonation;
import com.example.demo.iservices.IFoodDonationService;
import com.example.demo.repositories.IFoodDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FoodDonationServiceImpl implements IFoodDonationService {
    @Autowired
    private IFoodDonationRepository iFoodDonationRepository;

    @Override
    public FoodDonation save(FoodDonation foodDonation) {
        return iFoodDonationRepository.save(foodDonation);
    }

    @Override
    public void delete(Long id) {
        iFoodDonationRepository.deleteById(id);
    }

    @Override
    public List<FoodDonation> findall() {
        return iFoodDonationRepository.findAll();
    }

    @Override
    public FoodDonation update(FoodDonation foodDonation) {
        Optional<FoodDonation> foodDonationOptional = iFoodDonationRepository.findById(foodDonation.getId());
        if (foodDonationOptional.isPresent()) {
            FoodDonation updatedFoodDonation = foodDonationOptional.get();
            updatedFoodDonation.setFood_name(foodDonation.getFood_name());
            updatedFoodDonation.setSpecific_description(foodDonation.getSpecific_description());
            updatedFoodDonation.setBroadcast_date(foodDonation.getBroadcast_date());
            updatedFoodDonation.setExpiration_date(foodDonation.getExpiration_date());
            return iFoodDonationRepository.save(updatedFoodDonation);
        } else {
            throw new RuntimeException("FoodDonation not found with id: " + foodDonation.getId());
        }
    }



}
