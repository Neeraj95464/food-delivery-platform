package com.ynmio.OrderServices.service;

import com.ynmio.OrderServices.model.RestaurantData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RestaurantServices")
public interface RestaurantClient {

    @GetMapping("/restaurants/{id}")
    RestaurantData getRestaurantData(@PathVariable("id") Long id);
}

