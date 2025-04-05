package dao;

import connectDB.connectdata;
import entity.Ban;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ban_DAO {
    private Connection connection;

    public Ban_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection();
    }

    public boolean addBan(Ban ban) {
        String query = "INSERT INTO Ban (MaBan, SoLuongNguoi, TrangThai) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ban.getMaBan());
            stmt.setInt(2, ban.getSoLuongNguoi());
            stmt.setBoolean(3, ban.isTrangThai());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding Ban: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Ban getBan(String maBan) {
        String query = "SELECT MaBan, SoLuongNguoi, TrangThai FROM Ban WHERE MaBan = ?";
        Ban ban = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ban = new Ban(rs.getString("MaBan"), rs.getInt("SoLuongNguoi"), rs.getBoolean("TrangThai"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Ban: " + e.getMessage());
        }

        return ban;
    }
    public String getMaKhuFromMaBan(String maBan) {
        if (maBan.endsWith("V")) {
            return "VIP";
        }
        return "Thường";
    }

    public List<Ban> getAllBans() {
        List<Ban> bans = new ArrayList<>();
        String query = "SELECT MaBan, SoLuongNguoi, TrangThai FROM Ban";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ban ban = new Ban(rs.getString("MaBan"), rs.getInt("SoLuongNguoi"), rs.getBoolean("TrangThai"));
                bans.add(ban);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all Bans: " + e.getMessage());
        }

        return bans;
    }

    public boolean updateBan(Ban ban) {
        String query = "UPDATE Ban SET SoLuongNguoi = ?, TrangThai = ? WHERE MaBan = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ban.getSoLuongNguoi());
            stmt.setBoolean(2, ban.isTrangThai());
            stmt.setString(3, ban.getMaBan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating Ban: " + e.getMessage());
            return false;
        }
        return true ;
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
