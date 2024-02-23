package com.example.springbootdemo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test4 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3300/datart";
        String username = "root";
        String password = "gaosen";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTypeInfo();

            System.out.println("MySQL Supported Data Types:");
            while (resultSet.next()) {
                String typeName = resultSet.getString("TYPE_NAME");
                System.out.println(typeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
