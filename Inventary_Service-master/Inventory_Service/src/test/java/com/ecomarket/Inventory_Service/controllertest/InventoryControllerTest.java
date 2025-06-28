package com.ecomarket.Inventory_Service.controllertest;

import com.ecomarket.Inventory_Service.assembler.InventoryModelAssembler;
import com.ecomarket.Inventory_Service.controller.InventoryController;
import com.ecomarket.Inventory_Service.Exeption.ResourceNotFoundException;
import com.ecomarket.Inventory_Service.model.Inventory;
import com.ecomarket.Inventory_Service.model.InventoryModel;
import com.ecomarket.Inventory_Service.service.InventoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

        sampleModel = new InventoryModel(1L, 10, "Bodega Central");

        // Mock assembler behavior for single model
        when(assembler.toModel(sampleInventory)).thenReturn(sampleModel);

        // Mock assembler behavior for collection
        when(assembler.toCollectionModel(anyList()))
            .thenReturn(CollectionModel.of(List.of(sampleModel)));
    }

    @Test
    void testCreateInventory_withHateoas() {
        when(inventoryService.createInventory(any(Inventory.class))).thenReturn(sampleInventory);

        ResponseEntity<?> response = inventoryController.create(sampleInventory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }

    @Test
    void testGetAllInventory_withHateoas() {
        when(inventoryService.getAllInventory()).thenReturn(List.of(sampleInventory));

        ResponseEntity<?> response = inventoryController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof CollectionModel<?>);
        CollectionModel<?> body = (CollectionModel<?>) response.getBody();
        assertTrue(body.getContent().contains(sampleModel));
    }

    @Test
    void testGetInventoryById_withHateoas() {
        when(inventoryService.getInventoryById(1L)).thenReturn(sampleInventory);

        ResponseEntity<?> response = inventoryController.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }

    @Test
    void testUpdateInventory_withHateoas() {
        when(inventoryService.updateInventory(eq(1L), any(Inventory.class))).thenReturn(sampleInventory);

        ResponseEntity<?> response = inventoryController.update(1L, sampleInventory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }

    @Test
    void testDeleteInventory_success() {
        doNothing().when(inventoryService).deleteInventory(1L);

        ResponseEntity<?> response = inventoryController.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testIncreaseStock_withHateoas() {
        when(inventoryService.increaseStockById(1L, 5)).thenReturn(sampleInventory);

        ResponseEntity<?> response = inventoryController.increaseStockById(1L, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleModel, response.getBody());
    }

    @Test
    void testReduceStock_notFound() {
        when(inventoryService.reduceStockById(2L, 3))
            .thenThrow(new ResourceNotFoundException("No encontrado"));

        ResponseEntity<?> response = inventoryController.reduceStockById(2L, 3);

        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("No encontrado"));
    }
}