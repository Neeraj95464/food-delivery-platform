package com.ynmio.ResturantServices.service;

import com.ynmio.ResturantServices.model.Menu;
import com.ynmio.ResturantServices.model.MenuItem;
import com.ynmio.ResturantServices.model.Restaurant;
import com.ynmio.ResturantServices.repository.MenuItemRepository;
import com.ynmio.ResturantServices.repository.MenuRepository;
import com.ynmio.ResturantServices.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Optional<Restaurant> getSpecificMenuItem(Long restaurantId, Long menuId, Long menuItemId) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (restaurantOpt.isPresent()) {
            Restaurant restaurant = restaurantOpt.get();
            Optional<Menu> menuOpt = menuRepository.findById(menuId);
            if (menuOpt.isPresent() && menuOpt.get().getRestaurant().getId().equals(restaurantId)) {
                Menu menu = menuOpt.get();
                Optional<MenuItem> menuItemOpt = menuItemRepository.findById(menuItemId);
                if (menuItemOpt.isPresent() && menuItemOpt.get().getMenu().getId().equals(menuId)) {
                    MenuItem menuItem = menuItemOpt.get();
                    menu.setMenuItems(List.of(menuItem));
                    restaurant.setMenus(List.of(menu));
                    return Optional.of(restaurant);
                }
            }
        }
        return Optional.empty();
    }
}
