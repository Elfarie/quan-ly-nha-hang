/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectdata;
import entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
    private Connection connection;

    public NhanVien_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established before passing
    }

    public void addNhanVien(NhanVien nhanVien) {
        String query = "INSERT INTO NhanVien (MaNV, TenNV, SoDienTHoai) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getTenNV());
            stmt.setInt(3, nhanVien.getSoDienTHoai());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding NhanVien: " + e.getMessage());
        }
    }

    public NhanVien getNhanVien(String maNV) {
        String query = "SELECT MaNV, TenNV, SoDienTHoai FROM NhanVien WHERE MaNV = ?";
        NhanVien nhanVien = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nhanVien = new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getInt("SoDienTHoai"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving NhanVien: " + e.getMessage());
        }

        return nhanVien;
    }

    public List<NhanVien> getAllNhanViens() {
        List<NhanVien> nhanViens = new ArrayList<>();
        String query = "SELECT MaNV, TenNV, SoDienTHoai FROM NhanVien";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getInt("SoDienTHoai"));
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all NhanViens: " + e.getMessage());
        }

        return nhanViens;
    }

    public void updateNhanVien(NhanVien nhanVien) {
        String query = "UPDATE NhanVien SET TenNV = ?, SoDienTHoai = ? WHERE MaNV = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nhanVien.getTenNV());
            stmt.setInt(2, nhanVien.getSoDienTHoai());
            stmt.setString(3, nhanVien.getMaNV());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating NhanVien: " + e.getMessage());
        }
    }

    public void deleteNhanVien(String maNV) {
        String query = "DELETE FROM NhanVien WHERE MaNV = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting NhanVien: " + e.getMessage());
        }
    }
}
