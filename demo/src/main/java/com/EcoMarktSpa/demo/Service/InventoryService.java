package com.EcoMarktSpa.demo.Service;

import com.EcoMarktSpa.demo.Model.InventoryItem;
import com.EcoMarktSpa.demo.Repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    public List<InventoryItem> getAllItems() {
        return repo.findAll();
    }

    public Optional<InventoryItem> getItemById(Long id) {
        return repo.findById(id);
    }

    public InventoryItem saveItem(InventoryItem item) {
        return repo.save(item);
    }

    public void deleteItem(Long id) {
        repo.deleteById(id);
    }
}
