/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectdata;
import entity.MonAn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonAn_DAO {
    private Connection connection;

    public MonAn_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established
    }

    public void addMonAn(MonAn monAn) {
        String query = "INSERT INTO MonAn (MaMon, TenMon, DonGia) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, monAn.getMaMon());
            stmt.setString(2, monAn.getTenMon());
            stmt.setDouble(3, monAn.getDonGia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding MonAn: " + e.getMessage());
        }
    }

    public MonAn getMonAn(String maMon) {
        String query = "SELECT MaMon, TenMon, DonGia FROM MonAn WHERE MaMon = ?";
        MonAn monAn = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maMon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tenMon = rs.getString("TenMon");
                double donGia = rs.getDouble("DonGia");
                monAn = new MonAn(maMon, tenMon, donGia);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving MonAn: " + e.getMessage());
        }

        return monAn;
    }

    public List<MonAn> getAllMonAns() {
        List<MonAn> monAnList = new ArrayList<>();
        String query = "SELECT MaMon, TenMon, DonGia FROM MonAn";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String maMon = rs.getString("MaMon");
                String tenMon = rs.getString("TenMon");
                double donGia = rs.getDouble("DonGia");
                MonAn monAn = new MonAn(maMon, tenMon, donGia);
                monAnList.add(monAn);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all MonAns: " + e.getMessage());
        }

        return monAnList;
    }

    public void updateMonAn(MonAn monAn) {
        String query = "UPDATE MonAn SET TenMon = ?, DonGia = ? WHERE MaMon = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, monAn.getTenMon());
            stmt.setDouble(2, monAn.getDonGia());
            stmt.setString(3, monAn.getMaMon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating MonAn: " + e.getMessage());
        }
    }

    public void deleteMonAn(String maMon) {
        String query = "DELETE FROM MonAn WHERE MaMon = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maMon); // Specify which MonAn to delete by its ID
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting MonAn: " + e.getMessage());
        }
    }
}
