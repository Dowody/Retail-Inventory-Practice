package com.retail.model;

public class Product {

    private int id;
    private int categoryId;

    private final String sku;
    private String name;
    private int quantity;
    private int minQuantity;
    private double price;

    public Product(String sku, String name, int quantity, int minQuantity, double price, int categoryId) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be 0 or more");
        }
        if (minQuantity < 0) {
            throw new IllegalArgumentException("Minimum must be 0 or more");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be 0 or more");
        }
        if (categoryId <= 0) {
            throw new IllegalArgumentException("Category id must be above 0");
        }

        this.sku = sku.trim().toUpperCase();
        this.name = name.trim();
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(int id, String sku, String name, int quantity, int minQuantity, double price, int categoryId) {
        this(sku, name, quantity, minQuantity, price, categoryId);
        this.id = id;
    }

    // FUNCTIONS

    public boolean isLowStock() {
        return quantity <= minQuantity;
    }

    public double totalValue() {
        return quantity * price;
    }

    public void addStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be 1 or more");
        }

        quantity += amount;
    }
    public void removeStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be 1 or more");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("There are only " + quantity + " items available");
        }

        quantity -= amount;
    }




    // GETTERS
    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public double getPrice() {
        return price;
    }

    // Print product
    public String productPrint() {
        return sku + " | " + name + " | qty " + quantity
                + " | min " + minQuantity + " | price " + price;
    }

    public void setId (int id) {this.id = id;}

}
