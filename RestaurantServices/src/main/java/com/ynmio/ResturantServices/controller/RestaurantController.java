package com.ynmio.ResturantServices.controller;

import com.ynmio.ResturantServices.model.Restaurant;
import com.ynmio.ResturantServices.service.OrderService;
import com.ynmio.ResturantServices.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }
    @GetMapping("/order/{restaurantId}/{menuId}/{menuItemId}")
    public Optional<Restaurant> requestOrder(@PathVariable Long restaurantId, @PathVariable Long menuId, @PathVariable Long menuItemId){
        return orderService.getSpecificMenuItem(restaurantId,menuId,menuItemId);
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurantDetails) {
        return restaurantService.updateRestaurant(id, restaurantDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
}

