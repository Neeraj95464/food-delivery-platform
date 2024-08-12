package com.ynmio.ResturantServices.service;

import com.ynmio.ResturantServices.exception.ResourceNotFoundException;
import com.ynmio.ResturantServices.model.Menu;
import com.ynmio.ResturantServices.model.MenuItem;
import com.ynmio.ResturantServices.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuService menuService;

    public List<MenuItem> getMenuItemsByMenuId(Long menuId) {
        return menuItemRepository.findByMenuId(menuId);
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MenuItem not found"));
    }

    public MenuItem createMenuItem(Long menuId, MenuItem menuItem) {
        Menu menu = menuService.getMenuById(menuId);
        menuItem.setMenu(menu);
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItemDetails) {
        MenuItem menuItem = getMenuItemById(id);
        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setPrice(menuItemDetails.getPrice());
        menuItem.setImageUrl(menuItemDetails.getImageUrl());
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        MenuItem menuItem = getMenuItemById(id);
        menuItemRepository.delete(menuItem);
    }
}

