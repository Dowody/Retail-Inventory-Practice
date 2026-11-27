package com.retail;

import com.retail.db.CategoryRepository;
import com.retail.db.Database;
import com.retail.model.Category;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Database.init();

        CategoryRepository categoryRepository = new CategoryRepository();

        try {
            List<Category> categories = categoryRepository.findAll();

            for (Category cat: categories) {
                System.out.println("ID: " + cat.getId() + " | Name: " + cat.getName());
            }

            System.out.println("Worked");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}