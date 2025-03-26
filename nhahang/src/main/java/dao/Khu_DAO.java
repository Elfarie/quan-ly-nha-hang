/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectdata;
import entity.Khu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Khu_DAO {
    private Connection connection;

    public Khu_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established before passing
    }

    public void addKhu(Khu khu) {
        String query = "INSERT INTO Khu (MaKhu, TenKhu) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, khu.getMaKhu());
            stmt.setString(2, khu.getTenKhu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding Khu: " + e.getMessage());
        }
    }

    public Khu getKhu(String maKhu) {
        String query = "SELECT MaKhu, TenKhu FROM Khu WHERE MaKhu = ?";
        Khu khu = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maKhu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                khu = new Khu(rs.getString("MaKhu"), rs.getString("TenKhu"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Khu: " + e.getMessage());
        }

        return khu;
    }

    public List<Khu> getAllKhus() {
        List<Khu> khus = new ArrayList<>();
        String query = "SELECT MaKhu, TenKhu FROM Khu";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Khu khu = new Khu(rs.getString("MaKhu"), rs.getString("TenKhu"));
                khus.add(khu);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all Khus: " + e.getMessage());
        }

        return khus;
    }

    public void updateKhu(Khu khu) {
        String query = "UPDATE Khu SET TenKhu = ? WHERE MaKhu = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, khu.getTenKhu());
            stmt.setString(2, khu.getMaKhu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating Khu: " + e.getMessage());
        }
    }

    public void deleteKhu(String maKhu) {
        String query = "DELETE FROM Khu WHERE MaKhu = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maKhu);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting Khu: " + e.getMessage());
        }
    }
}
