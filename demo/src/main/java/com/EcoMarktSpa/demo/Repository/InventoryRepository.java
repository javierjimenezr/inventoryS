package com.EcoMarktSpa.demo.Repository;

import com.EcoMarktSpa.demo.Model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {}
