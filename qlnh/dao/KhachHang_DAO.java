/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectdata;
import entity.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {
    private Connection connection;

    public KhachHang_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established before passing
    }

    public void addKhachHang(KhachHang khachHang) {
        String query = "INSERT INTO KhachHang (MaKH, TenKH, SoDienTHoai) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, khachHang.getMaKH());
            stmt.setString(2, khachHang.getTenKH());
            stmt.setInt(3, khachHang.getSoDienTHoai());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding KhachHang: " + e.getMessage());
        }
    }

    public KhachHang getKhachHang(String maKH) {
        String query = "SELECT MaKH, TenKH, SoDienTHoai FROM KhachHang WHERE MaKH = ?";
        KhachHang khachHang = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                khachHang = new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"), rs.getInt("SoDienTHoai"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving KhachHang: " + e.getMessage());
        }

        return khachHang;
    }

    public List<KhachHang> getAllKhachHangs() {
        List<KhachHang> khachHangs = new ArrayList<>();
        String query = "SELECT MaKH, TenKH, SoDienTHoai FROM KhachHang";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                KhachHang khachHang = new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"), rs.getInt("SoDienTHoai"));
                khachHangs.add(khachHang);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all KhachHangs: " + e.getMessage());
        }

        return khachHangs;
    }

    public void updateKhachHang(KhachHang khachHang) {
        String query = "UPDATE KhachHang SET TenKH = ?, SoDienTHoai = ? WHERE MaKH = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, khachHang.getTenKH());
            stmt.setInt(2, khachHang.getSoDienTHoai());
            stmt.setString(3, khachHang.getMaKH());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating KhachHang: " + e.getMessage());
        }
    }

    public void deleteKhachHang(String maKH) {
        String query = "DELETE FROM KhachHang WHERE MaKH = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maKH);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting KhachHang: " + e.getMessage());
        }
    }
}
