package com.ynmio.ResturantServices.controller;

import com.ynmio.ResturantServices.model.MenuItem;
import com.ynmio.ResturantServices.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/menu/{menuId}")
    public List<MenuItem> getMenuItemsByMenuId(@PathVariable Long menuId) {
        return menuItemService.getMenuItemsByMenuId(menuId);
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping("/menu/{menuId}")
    public MenuItem createMenuItem(@PathVariable Long menuId, @RequestBody MenuItem menuItem) {
        return menuItemService.createMenuItem(menuId, menuItem);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItemDetails) {
        return menuItemService.updateMenuItem(id, menuItemDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}

