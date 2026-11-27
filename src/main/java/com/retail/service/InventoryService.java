package com.retail.service;

import com.retail.db.CategoryRepository;
import com.retail.db.ProductRepository;
import com.retail.model.Product;

import java.sql.SQLException;

public class InventoryService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InventoryService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void restock(String sku, int amount) throws SQLException {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + sku));

        product.addStock(amount);
        productRepository.updateQuantity(product);
    }

    public void sell(String sku, int amount) throws SQLException {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + sku));

        product.removeStock(amount);
        productRepository.updateQuantity(product);
    }
}
