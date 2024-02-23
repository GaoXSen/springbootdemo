package com.example.springbootdemo.util;

import java.sql.*;

public class Test5 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3300/demo";
        String username = "root";
        String password = "gaosen";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            DatabaseMetaData metaData = conn.getMetaData();
            rs = metaData.getColumns(null, null, "sys_role3", null);
            while (rs.next()) {

                System.out.print(rs.getString(1) + "---");
                System.out.print(rs.getString(2) + "---");
                System.out.print(rs.getString(3) + "---");
                System.out.print(rs.getString(4) + "---");
                System.out.print(rs.getString(5) + "---");
                System.out.print(rs.getString(6) + "---");
                System.out.print(rs.getString(7) + "---");
                System.out.print(rs.getString(8) + "---");
                System.out.print(rs.getString(9) + "---");
                System.out.print(rs.getString(10) + "---");
                System.out.print(rs.getString(11) + "---");
                System.out.print(rs.getString(12) + "---");
                System.out.print(rs.getString(13) + "---");
                System.out.print(rs.getString(14) + "---");
                System.out.print(rs.getString(15) + "---");
                System.out.print(rs.getString(16) + "---");
                System.out.print(rs.getString(17) + "---");
                System.out.print(rs.getString(18) + "---");

                System.out.println();

                String columnName = rs.getString("COLUMN_NAME");
//                System.out.println(columnName + "---" + rs.getString(6) + "---" + rs.getInt("COLUMN_SIZE") + "---" + rs.getInt("DECIMAL_DIGITS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

