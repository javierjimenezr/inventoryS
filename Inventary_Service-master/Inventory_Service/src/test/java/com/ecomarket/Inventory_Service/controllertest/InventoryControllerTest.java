package com.ecomarket.Inventory_Service.controllertest;


import com.ecomarket.Inventory_Service.Exeption.ResourceNotFoundException;
import com.ecomarket.Inventory_Service.controller.InventoryController;
import com.ecomarket.Inventory_Service.model.Inventory;
import com.ecomarket.Inventory_Service.model.InventoryModel;
import com.ecomarket.Inventory_Service.assembler.InventoryModelAssembler;
import com.ecomarket.Inventory_Service.service.InventoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @Mock
    private InventoryModelAssembler assembler;

    @InjectMocks
    private InventoryController inventoryController;

    private Inventory sampleInventory;
    private InventoryModel sampleModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleInventory = Inventory.builder()
                .id(1L)
                .quantity(10)
                .location("Bodega Central")
                .build();

        sampleModel = new InventoryModel();
        sampleModel.addLink("self", "/inventory/1");  // simulación básica
    }

    @Test
    void testGetInventoryById_withHateoas() {
        when(inventoryService.getInventoryById(1L)).thenReturn(sampleInventory);
        when(assembler.toModel(sampleInventory)).thenReturn(sampleModel);

        ResponseEntity<?> response = inventoryController.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }

    @Test
    void testGetAllInventory_withHateoas() {
        List<Inventory> inventories = Arrays.asList(sampleInventory);
        List<InventoryModel> models = Arrays.asList(sampleModel);
        CollectionModel<InventoryModel> collection = CollectionModel.of(models);

        when(inventoryService.getAllInventory()).thenReturn(inventories);
        when(assembler.toCollectionModel(inventories)).thenReturn(collection);

        ResponseEntity<?> response = inventoryController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(collection, response.getBody());
    }

    @Test
    void testCreateInventory_withHateoas() {
        when(inventoryService.createInventory(any(Inventory.class))).thenReturn(sampleInventory);
        when(assembler.toModel(sampleInventory)).thenReturn(sampleModel);

        ResponseEntity<?> response = inventoryController.create(sampleInventory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }

    @Test
    void testUpdateInventory_withHateoas() {
        when(inventoryService.updateInventory(eq(1L), any(Inventory.class))).thenReturn(sampleInventory);
        when(assembler.toModel(sampleInventory)).thenReturn(sampleModel);

        ResponseEntity<?> response = inventoryController.update(1L, sampleInventory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }
}
