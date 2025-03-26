/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectdata;
import entity.Ban;
import entity.Khu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ban_DAO {
    private Connection connection;

    public Ban_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established before passing
    }


    public void addBan(Ban ban) {
        String query = "INSERT INTO Ban (MaBan, Khu_MaKhu, SoLuongNguoi, TrangThai, DonGia) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ban.getMaBan());
            stmt.setString(2, ban.getKhu().getMaKhu()); // Assume Khu has a method getMaKhu
            stmt.setInt(3, ban.getSoLuongNguoi());
            stmt.setBoolean(4, ban.isTrangThai());
            stmt.setDouble(5, ban.getDonGia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding Ban: " + e.getMessage());
        }
    }

    public Ban getBan(String maBan) {
        String query = "SELECT MaBan, Khu_MaKhu, SoLuongNguoi, TrangThai, DonGia FROM Ban WHERE MaBan = ?";
        Ban ban = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Khu khu = new Khu(rs.getString("Khu_MaKhu")); // Assume Khu constructor can take MaKhu
                ban = new Ban(rs.getString("MaBan"), khu, rs.getInt("SoLuongNguoi"), rs.getBoolean("TrangThai"), rs.getDouble("DonGia"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Ban: " + e.getMessage());
        }

        return ban;
    }

    public List<Ban> getAllBans() {
        List<Ban> bans = new ArrayList<>();
        String query = "SELECT MaBan, Khu_MaKhu, SoLuongNguoi, TrangThai, DonGia FROM Ban";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Khu khu = new Khu(rs.getString("Khu_MaKhu"));
                Ban ban = new Ban(rs.getString("MaBan"), khu, rs.getInt("SoLuongNguoi"), rs.getBoolean("TrangThai"), rs.getDouble("DonGia"));
                bans.add(ban);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all Bans: " + e.getMessage());
        }

        return bans;
    }

    public void updateBan(Ban ban) {
        String query = "UPDATE Ban SET Khu_MaKhu = ?, SoLuongNguoi = ?, TrangThai = ?, DonGia = ? WHERE MaBan = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ban.getKhu().getMaKhu());
            stmt.setInt(2, ban.getSoLuongNguoi());
            stmt.setBoolean(3, ban.isTrangThai());
            stmt.setDouble(4, ban.getDonGia());
            stmt.setString(5, ban.getMaBan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating Ban: " + e.getMessage());
        }
    }

    public void deleteBan(String maBan) {
        String query = "DELETE FROM Ban WHERE MaBan = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maBan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting Ban: " + e.getMessage());
        }
    }
}
