package com.sidof.model;

import com.sidof.enume.HouseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

/*
 *   Author: sidof
 *   Since : 28/05/2024
*/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_sequence")
    @SequenceGenerator(name = "house_sequence",sequenceName = "house_sequence",allocationSize = 1)
    private Long id;
    @Enumerated(STRING)
    private HouseType houseType;
    private String address;
    private String city;
    private int numBedrooms;
    private int numBathrooms;
//    private List<String> amenities;
    private double rentPrice;
    private List<String> imageUrl;

    public House(Long id, HouseType houseType, String address, String city, int numBedrooms, int numBathrooms, double rentPrice) {
        this.id = id;
        this.houseType = houseType;
        this.address = address;
        this.city = city;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.rentPrice = rentPrice;
    }
}
