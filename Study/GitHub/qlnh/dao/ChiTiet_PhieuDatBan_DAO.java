package dao;

import connectDB.connectdata;
import entity.Ban;
import entity.ChiTiet_PhieuDatBan;
import entity.HoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTiet_PhieuDatBan_DAO {
    private Connection connection;

    public ChiTiet_PhieuDatBan_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established before passing
    }

    public void addChiTiet_PhieuDatBan(ChiTiet_PhieuDatBan chiTiet) {
        String query = "INSERT INTO ChiTiet_PhieuDatBan (MaCTPhieu, Ban_MaBan, HoaDon_MaHD) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getMaCTPhieu());
            stmt.setString(2, chiTiet.getBan().getMaBan()); // Assume Ban has a method getMaBan()
            stmt.setString(3, chiTiet.getHd().getMaHD()); // Assume HoaDon has a method getMaHD()
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding ChiTiet_PhieuDatBan: " + e.getMessage());
        }
    }

    public ChiTiet_PhieuDatBan getChiTiet_PhieuDatBan(String maCTPhieu) {
        String query = "SELECT MaCTPhieu, Ban_MaBan, HoaDon_MaHD FROM ChiTiet_PhieuDatBan WHERE MaCTPhieu = ?";
        ChiTiet_PhieuDatBan chiTiet = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maCTPhieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Ban ban = new Ban(rs.getString("Ban_MaBan")); // Assume constructor exists for Ban
                HoaDon hd = new HoaDon(rs.getString("HoaDon_MaHD")); // Assume constructor exists for HoaDon
                chiTiet = new ChiTiet_PhieuDatBan(rs.getString("MaCTPhieu"), ban, hd);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving ChiTiet_PhieuDatBan: " + e.getMessage());
        }

        return chiTiet;
    }

    public List<ChiTiet_PhieuDatBan> getAllChiTiet_PhieuDatBans() {
        List<ChiTiet_PhieuDatBan> chiTietList = new ArrayList<>();
        String query = "SELECT MaCTPhieu, Ban_MaBan, HoaDon_MaHD FROM ChiTiet_PhieuDatBan";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ban ban = new Ban(rs.getString("Ban_MaBan"));
                HoaDon hd = new HoaDon(rs.getString("HoaDon_MaHD"));
                ChiTiet_PhieuDatBan chiTiet = new ChiTiet_PhieuDatBan(rs.getString("MaCTPhieu"), ban, hd);
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all ChiTiet_PhieuDatBans: " + e.getMessage());
        }

        return chiTietList;
    }

    public void deleteChiTiet_PhieuDatBan(String maCTPhieu) {
        String query = "DELETE FROM ChiTiet_PhieuDatBan WHERE MaCTPhieu = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maCTPhieu);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting ChiTiet_PhieuDatBan: " + e.getMessage());
        }
    }
}