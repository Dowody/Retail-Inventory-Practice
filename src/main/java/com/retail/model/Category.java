package com.retail.model;

public class Category {



    private int id;
    private final String name;


    public Category (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }

        this.name = name.trim();
    }

    public Category (int id, String name) {
        this(name);

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId (int id) {this.id = id;}

    // Print category
    public String categoryPrint() {
        return id + " | " + name;
    }
}


