package com.ynmio.ResturantServices.controller;

import com.ynmio.ResturantServices.model.Menu;
import com.ynmio.ResturantServices.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/restaurant/{restaurantId}")
    public List<Menu> getMenusByRestaurantId(@PathVariable Long restaurantId) {
        return menuService.getMenusByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public Menu createMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        return menuService.createMenu(restaurantId, menu);
    }

    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        return menuService.updateMenu(id, menuDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }
}

