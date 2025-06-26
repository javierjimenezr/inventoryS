package com.ecomarket.Inventory_Service.repository;


import com.ecomarket.Inventory_Service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Sin métodos personalizados por ahora
}

