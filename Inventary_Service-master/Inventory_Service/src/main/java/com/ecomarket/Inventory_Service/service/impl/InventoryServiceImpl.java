package com.ecomarket.Inventory_Service.service.impl;


import com.ecomarket.Inventory_Service.model.Inventory;
import com.ecomarket.Inventory_Service.repository.InventoryRepository;
import com.ecomarket.Inventory_Service.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        inventory.setId(null);
        return repository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory with ID " + id + " not found."));

        existing.setQuantity(inventory.getQuantity());
        existing.setLocation(inventory.getLocation());

        return repository.save(existing);
    }

    @Override
    public void deleteInventory(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Inventory with ID " + id + " does not exist.");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return repository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory with ID " + id + " not found."));
    }

    @Override
    public Inventory reduceStockById(Long id, int amount) {
        Inventory inventory = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory with ID " + id + " not found."));

        if (inventory.getQuantity() < amount) {
            throw new RuntimeException("Not enough stock. Available: " + inventory.getQuantity());
        }

        inventory.setQuantity(inventory.getQuantity() - amount);
        return repository.save(inventory);
    }

    @Override
public Inventory increaseStockById(Long id, int amount) {
    Inventory inventory = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventory with ID " + id + " not found."));

    inventory.setQuantity(inventory.getQuantity() + amount);
    return repository.save(inventory);
}
}