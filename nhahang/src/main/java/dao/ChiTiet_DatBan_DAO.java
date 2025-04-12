package dao;

import connectDB.connectdata;
import entity.Ban;
import entity.ChiTiet_DatBan;
import entity.HoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTiet_DatBan_DAO {
    private Connection connection;

    public ChiTiet_DatBan_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection();
    }

    public void addChiTiet_DatBan(ChiTiet_DatBan chiTiet) {
        String query = "INSERT INTO ChiTiet_DatBan (MaBan, MaHD) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getBan().getMaBan());
            stmt.setString(2, chiTiet.getHd().getMaHD());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding ChiTiet_DatBan: " + e.getMessage());
        }
    }

    public List<ChiTiet_DatBan> getAllChiTiet_DatBans() {
        List<ChiTiet_DatBan> chiTietList = new ArrayList<>();
        String query = "SELECT MaBan, MaHD FROM ChiTiet_DatBan";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ban ban = new Ban(rs.getString("MaBan"));
                HoaDon hd = new HoaDon(rs.getString("MaHD"));
                ChiTiet_DatBan chiTiet = new ChiTiet_DatBan(ban, hd);
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all ChiTiet_DatBans: " + e.getMessage());
        }

        return chiTietList;
    }

    public void deleteChiTiet_DatBan(String maBan, String maHD) {
        String query = "DELETE FROM ChiTiet_DatBan WHERE MaBan = ? AND MaHD = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maBan);
            stmt.setString(2, maHD);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting ChiTiet_DatBan: " + e.getMessage());
        }
    }
}
