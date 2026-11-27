package com.retail.db;

import com.retail.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    public void add(Product product) throws SQLException {
        String sql = "INSERT INTO products (sku, name, quantity, min_quantity, price, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, product.getSku());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getMinQuantity());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getCategoryId());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys())
            {
                if (keys.next()) {
                    product.setId(keys.getInt(1));
                }
            }
        }
        }


        public List<Product> findAll() throws SQLException {
            String sql = "SELECT id, sku, name, quantity, min_quantity, price, category_id FROM products ORDER BY sku";
            List<Product> products = new ArrayList<>();

            try (Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("sku"),
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            rs.getInt("min_quantity"),
                            rs.getDouble("price"),
                            rs.getInt("category_id")
                    );
                    products.add(product);
                }
            }

            return products;
        }

        public Optional<Product> findBySku (String sku) throws SQLException {
            String sql = "SELECT id, sku, name, quantity, min_quantity, price, category_id FROM products WHERE sku = ?";

            try (Connection c = Database.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
            ) {

                ps.setString(1, sku.trim().toUpperCase());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                            Product product = new Product(
                                    rs.getInt("id"),
                                    rs.getString("sku"),
                                    rs.getString("name"),
                                    rs.getInt("quantity"),
                                    rs.getInt("min_quantity"),
                                    rs.getDouble("price"),
                                    rs.getInt("category_id")
                            );
                            return Optional.of(product);
                    }
                }


            }
            return Optional.empty();
        }

        public void updateQuantity(Product product) throws SQLException {
            String sql = "UPDATE products SET quantity = ? WHERE id = ?";
            try (Connection c = Database.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setInt(1, product.getQuantity());
                ps.setInt(2, product.getId());

                ps.executeUpdate();

            }
        }
}
