package com.mystore.store_api.service;

import com.mystore.store_api.exception.BusinessException;
import com.mystore.store_api.model.Inventory;
import com.mystore.store_api.model.Product;
import com.mystore.store_api.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory create(Product product, int quantity) {
        try {
            Inventory inventory = new Inventory();

            inventory.setProduct(product);
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);

            return inventory;
        } catch (Exception e) {
            throw new BusinessException("Error registering product inventory.");
        }
    }
}
