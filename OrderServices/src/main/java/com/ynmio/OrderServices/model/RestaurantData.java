package com.ynmio.OrderServices.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RestaurantData {

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String description;
    private String imageUrl;
}
