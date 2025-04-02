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

    public boolean addKhachHang(KhachHang khachHang) {
        String query = "INSERT INTO KhachHang (MaKH, TenKH, SoDienTHoai) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, khachHang.getMaKH());
            stmt.setString(2, khachHang.getTenKH());
            stmt.setString(3, khachHang.getSoDienTHoai());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding KhachHang: " + e.getMessage());
            return false;
        }
    }

    public KhachHang getKhachHang(String maKH) {
        String query = "SELECT MaKH, TenKH, SoDienTHoai FROM KhachHang WHERE MaKH = ?";
        KhachHang khachHang = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                khachHang = new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"), rs.getString("SoDienTHoai"));
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
                KhachHang khachHang = new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"), rs.getString("SoDienTHoai"));
                khachHangs.add(khachHang);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all KhachHangs: " + e.getMessage());
        }

        return khachHangs;
    }

    public boolean updateKhachHang(KhachHang khachHang) {
    String query = "UPDATE KhachHang SET TenKH = ?, MaKH = ? WHERE SoDienTHoai = ?"; // Sửa thứ tự tham số trong câu lệnh SQL

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, khachHang.getTenKH()); // Đặt tham số TenKH vào vị trí đầu tiên
        stmt.setString(2, khachHang.getMaKH()); // Đặt tham số MaKH vào vị trí thứ hai
        stmt.setString(3, khachHang.getSoDienTHoai()); // Đặt tham số SoDienTHoai vào vị trí thứ ba

        int rowsAffected = stmt.executeUpdate(); // Lấy số dòng bị ảnh hưởng

        if (rowsAffected == 0) {
            System.out.println("Không tìm thấy khách hàng với Số điện thoại: " + khachHang.getSoDienTHoai()); // Thông báo lỗi với số điện thoại
            return false;
        }
        return true;
    } catch (SQLException e) {
        System.err.println("Lỗi cập nhật khách hàng: " + e.getMessage());
        return false;
    }
    }


    public boolean deleteKhachHang(String maKH) {
        String query = "DELETE FROM KhachHang WHERE MaKH = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maKH);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting KhachHang: " + e.getMessage());
            return false;
        }
    }
}

