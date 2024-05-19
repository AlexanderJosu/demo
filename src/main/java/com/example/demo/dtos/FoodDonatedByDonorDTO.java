package com.example.demo.dtos;

import com.example.demo.entities.FoodDonation;
import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDonatedByDonorDTO {
    private Long id;
    private LocalDate donationdate;
    private Long quantityFood;
    private User users;
    private FoodDonation foodDonation;
}
