package com.retail.db;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {


    public static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        return DriverManager.getConnection("jdbc:sqlite:retail.db", config.toProperties());
    }




    public static void init() {
        try (Connection c = getConnection();
             Statement s = c.createStatement())
        {



            String createCategories = """
                        CREATE TABLE IF NOT EXISTS categories (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL UNIQUE
                        );
                    """;
            String createProducts = """ 
                        CREATE TABLE IF NOT EXISTS products (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        sku TEXT NOT NULL UNIQUE,
                        name TEXT NOT NULL,
                        quantity INTEGER NOT NULL CHECK (quantity >= 0),
                        min_quantity INTEGER NOT NULL CHECK (min_quantity >= 0),
                        price REAL NOT NULL CHECK (price >= 0),
                        category_id INTEGER NOT NULL,
                        FOREIGN KEY (category_id) REFERENCES categories(id)
                        );
                    """;

            s.execute(createCategories);
            s.execute(createProducts);


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
