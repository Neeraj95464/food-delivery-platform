package com.ynmio.ResturantServices.service;

import com.ynmio.ResturantServices.exception.ResourceNotFoundException;
import com.ynmio.ResturantServices.model.Menu;
import com.ynmio.ResturantServices.model.Restaurant;
import com.ynmio.ResturantServices.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<Menu> getMenusByRestaurantId(Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found"));
    }

    public Menu createMenu(Long restaurantId, Menu menu) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, Menu menuDetails) {
        Menu menu = getMenuById(id);
        menu.setName(menuDetails.getName());
        menu.setDescription(menuDetails.getDescription());
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        Menu menu = getMenuById(id);
        menuRepository.delete(menu);
    }
}

