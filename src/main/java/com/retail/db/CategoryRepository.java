package com.retail.db;
import com.retail.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public void add(Category category) throws SQLException {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, category.getName());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys())
            {
                if (keys.next()) {
                    category.setId(keys.getInt(1));
                }
            }
        }
    }

    public List<Category> findAll() throws SQLException {

        List<Category> list = new ArrayList<>();
        String sql = "SELECT id, name FROM categories ORDER BY name";

        try (Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );

                list.add(category);
            }
        }

        return list;
    }



}
