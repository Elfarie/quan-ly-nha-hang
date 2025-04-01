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
        this.connection = conn.getConnection();
    }

    // Thêm nhân viên
    public boolean addNhanVien(NhanVien nhanVien) {
        String query = "INSERT INTO NhanVien (MaNV, TenNV, SoDienThoai) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getTenNV());
            stmt.setString(3, nhanVien.getSoDienThoai()); // Chuyển đổi sang String
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm nhân viên: " + e.getMessage());
            return false;
        }
    }

    // Lấy nhân viên theo mã NV
    public NhanVien getNhanVien(String maNV) {
        String query = "SELECT MaNV, TenNV, SoDienThoai FROM NhanVien WHERE MaNV = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("SoDienThoai"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy nhân viên: " + e.getMessage());
        }
        return null;
    }

    // Lấy tất cả nhân viên
    public List<NhanVien> getAllNhanViens() {
        List<NhanVien> nhanViens = new ArrayList<>();
        String query = "SELECT MaNV, TenNV, SoDienThoai FROM NhanVien";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                nhanViens.add(new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("SoDienThoai")));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách nhân viên: " + e.getMessage());
        }
        return nhanViens;
    }

    // Cập nhật nhân viên
    public boolean updateNhanVien(NhanVien nhanVien) {
        String query = "UPDATE NhanVien SET TenNV = ?, SoDienThoai = ? WHERE MaNV = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nhanVien.getTenNV());
            stmt.setString(2, nhanVien.getSoDienThoai()); // Chuyển sang String
            stmt.setString(3, nhanVien.getMaNV());
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật nhân viên: " + e.getMessage());
            return false;
        }
    }

    // Xóa nhân viên
    public boolean deleteNhanVien(String maNV) {
        String query = "DELETE FROM NhanVien WHERE MaNV = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa nhân viên: " + e.getMessage());
            return false;
        }
    }
}
