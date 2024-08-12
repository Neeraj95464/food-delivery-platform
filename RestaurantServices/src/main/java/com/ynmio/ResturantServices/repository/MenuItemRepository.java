package com.ynmio.ResturantServices.repository;

import com.ynmio.ResturantServices.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem ,Long> {
    List<MenuItem> findByMenuId(Long menuId);
}
