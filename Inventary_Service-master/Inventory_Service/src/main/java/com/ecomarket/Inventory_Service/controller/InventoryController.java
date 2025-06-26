package com.ecomarket.Inventory_Service.controller;

import com.ecomarket.Inventory_Service.model.Inventory;
import com.ecomarket.Inventory_Service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public Inventory create(@RequestBody Inventory inventory) {
        return service.createInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory update(@PathVariable Long id, @RequestBody Inventory inventory) {
        return service.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteInventory(id);
    }

    @GetMapping
    public List<Inventory> getAll() {
        return service.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getById(@PathVariable Long id) {
        return service.getInventoryById(id);
    }

    @PostMapping("/{id}/reduce-stock")
    public Inventory reduceStockById(@PathVariable Long id, @RequestParam int amount) {
        return service.reduceStockById(id, amount);
    }
    
    @PostMapping("/{id}/increase-stock")
    public Inventory increaseStockById(@PathVariable Long id, @RequestParam int amount) {
        return service.increaseStockById(id, amount);
    }
}