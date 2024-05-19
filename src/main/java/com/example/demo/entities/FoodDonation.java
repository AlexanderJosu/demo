package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "food_donation")
public class FoodDonation {
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_donation_id")
    private Long id; //id food donation
    //Nombre_Alimento
    @Column(name = "food_name", nullable = false)
    private String food_name; //food name
    //Descrtipcion_espeficico
    @Column(name = "specific_description", nullable = false)
    private String specific_description; //specific description of the food donated
    //Fecha_emision
    @Column(name = "broadcast_date", nullable = false)
    private LocalDate broadcast_date; //broadcast date of the food donated
    //Fecha_vencimiento
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expiration_date; //expiration date of the food donated

    @ManyToOne
    @JoinColumn(name = "type_of_food_id", nullable = false)
    private TypeOfFood typeOfFood;


}
