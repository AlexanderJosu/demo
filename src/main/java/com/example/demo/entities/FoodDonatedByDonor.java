package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "food_donated_by_donor")
public class FoodDonatedByDonor {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_donated_by_donor_id")
    private Long id;

    //Donation date
    @Column(name = "donationdate", nullable = false)
    private LocalDate donationdate;

    //Amount of food donated
    @Column(name = "quantityFood", nullable = false)
    private Long quantityFood;

    //user(donor)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    //food_donation
    @ManyToOne
    @JoinColumn(name = "food_donation_id", nullable = false)
    private FoodDonation foodDonation;
}
