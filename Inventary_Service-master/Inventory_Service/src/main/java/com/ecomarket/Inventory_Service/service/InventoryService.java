package com.ecomarket.Inventory_Service.service;


import com.ecomarket.Inventory_Service.model.Inventory;
import java.util.List;

public interface InventoryService {
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Long id, Inventory inventory);
    void deleteInventory(Long id);
    List<Inventory> getAllInventory();
    Inventory getInventoryById(Long id);
    Inventory reduceStockById(Long id, int amount);
    Inventory increaseStockById(Long id, int amount);
}