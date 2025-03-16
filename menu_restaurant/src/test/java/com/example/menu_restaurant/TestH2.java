package com.example.menu_restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestH2 {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb;MODE=PostgreSQL;DATABASE_TO_UPPER=false";
        String user = "sa";
        String password = "sa";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Успешное подключение к H2!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}
