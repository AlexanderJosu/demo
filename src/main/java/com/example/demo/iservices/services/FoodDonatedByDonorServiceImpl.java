package com.example.demo.iservices.services;

import com.example.demo.entities.FoodDonatedByDonor;
import com.example.demo.iservices.IFoodDonatedByDonorService;
import com.example.demo.iservices.IUserService;
import com.example.demo.repositories.IFoodDonatedByDonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FoodDonatedByDonorServiceImpl implements IFoodDonatedByDonorService {
    @Autowired
    private IUserService userService;

    @Autowired
    private IFoodDonatedByDonorRepository iFoodDonatedByDonorRepository;

    @Override
    public List<FoodDonatedByDonor> findAll() {
        return iFoodDonatedByDonorRepository.findAll();
    }

    @Override
    public FoodDonatedByDonor findFirstByUser(Long userId) {
        return iFoodDonatedByDonorRepository.findFirstByUser(userId);
    }

    @Override
    public FoodDonatedByDonor update(FoodDonatedByDonor foodDonatedByDonor) {
        Optional<FoodDonatedByDonor> existingFoodDonatedByDonor = iFoodDonatedByDonorRepository.findById(foodDonatedByDonor.getId());
        if (existingFoodDonatedByDonor.isPresent()) {
            FoodDonatedByDonor updatedFoodDonatedByDonor = existingFoodDonatedByDonor.get();
            updatedFoodDonatedByDonor.setDonationdate(foodDonatedByDonor.getDonationdate());
            updatedFoodDonatedByDonor.setQuantityFood(foodDonatedByDonor.getQuantityFood());
            return iFoodDonatedByDonorRepository.save(updatedFoodDonatedByDonor);
        } else {
            throw new RuntimeException("FoodDonatedByDonor not found with id: " + foodDonatedByDonor.getId());
        }
    }




    @Override
    public void delete(Long id) {
        iFoodDonatedByDonorRepository.deleteById(id);
    }

    @Override
    public FoodDonatedByDonor save(FoodDonatedByDonor foodDonatedByDonor) {
        return iFoodDonatedByDonorRepository.save(foodDonatedByDonor);
    }

}
