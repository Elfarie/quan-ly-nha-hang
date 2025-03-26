/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectdata;
import entity.NhanVien;
import entity.TK_NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TK_NhanVien_DAO {
    private Connection connection;

    public TK_NhanVien_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established before passing
    }

    public void addTK_NhanVien(TK_NhanVien tk_nhanVien) {
        String query = "INSERT INTO TK_NhanVien (MaTK, MatKhauTK, MaNV) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tk_nhanVien.getMaTK());
            stmt.setString(2, tk_nhanVien.getMatKhauTK());
            stmt.setString(3, tk_nhanVien.getNv().getMaNV()); // Assume NhanVien has getMaNV() method
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding TK_NhanVien: " + e.getMessage());
        }
    }

    public TK_NhanVien getTK_NhanVien(String maTK) {
        String query = "SELECT MaTK, MatKhauTK, MaNV FROM TK_NhanVien WHERE MaTK = ?";
        TK_NhanVien tk_nhanVien = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maTK);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNV")); // Assume constructor exists for NhanVien
                tk_nhanVien = new TK_NhanVien(nv, rs.getString("MatKhauTK"), rs.getString("MaTK"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving TK_NhanVien: " + e.getMessage());
        }

        return tk_nhanVien;
    }

    public List<TK_NhanVien> getAllTK_NhanViens() {
        List<TK_NhanVien> tk_nhanViens = new ArrayList<>();
        String query = "SELECT MaTK, MatKhauTK, MaNV FROM TK_NhanVien";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNV"));
                TK_NhanVien tk_nhanVien = new TK_NhanVien(nv, rs.getString("MatKhauTK"), rs.getString("MaTK"));
                tk_nhanViens.add(tk_nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all TK_NhanViens: " + e.getMessage());
        }

        return tk_nhanViens;
    }

    public void updateTK_NhanVien(TK_NhanVien tk_nhanVien) {
        String query = "UPDATE TK_NhanVien SET MatKhauTK = ?, MaNV = ? WHERE MaTK = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tk_nhanVien.getMatKhauTK());
            stmt.setString(2, tk_nhanVien.getNv().getMaNV());
            stmt.setString(3, tk_nhanVien.getMaTK());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating TK_NhanVien: " + e.getMessage());
        }
    }

    public void deleteTK_NhanVien(String maTK) {
        String query = "DELETE FROM TK_NhanVien WHERE MaTK = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maTK);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting TK_NhanVien: " + e.getMessage());
        }
    }
}
