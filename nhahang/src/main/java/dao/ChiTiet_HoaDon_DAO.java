package dao;

import connectDB.connectdata;
import entity.ChiTiet_HoaDon;
import entity.HoaDon; // Assuming you have the HoaDon entity
import entity.MonAn; // Assuming you have the MonAn entity
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTiet_HoaDon_DAO {
    private Connection connection;

    public ChiTiet_HoaDon_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established
    }

    public void addChiTietHoaDon(ChiTiet_HoaDon chiTiet) {
        String query = "INSERT INTO ChiTiet_HoaDon (MaCTHD, MaHD, MaMon, SoLuong) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getMaCTHD());
            stmt.setString(2, chiTiet.getHd().getMaHD()); // Assuming HoaDon has a getMaHD method
            stmt.setString(3, chiTiet.getMon().getMaMon()); // Assuming MonAn has a getMaMon method
            stmt.setInt(4, chiTiet.getSoLuong());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding ChiTiet_HoaDon: " + e.getMessage());
        }
    }

    public ChiTiet_HoaDon getChiTietHoaDon(String maCTHD) {
        String query = "SELECT MaCTHD, MaHD, MaMon, SoLuong FROM ChiTiet_HoaDon WHERE MaCTHD = ?";
        ChiTiet_HoaDon chiTiet = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maCTHD);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maMon = rs.getString("MaMon");
                int soLuong = rs.getInt("SoLuong");
                
                HoaDon hd = new HoaDon(maHD); // You would retrieve full HoaDon details
                MonAn mon = new MonAn(maMon); // You would retrieve full MonAn details
                
                chiTiet = new ChiTiet_HoaDon(maCTHD, hd, mon, soLuong);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving ChiTiet_HoaDon: " + e.getMessage());
        }

        return chiTiet;
    }

    public List<ChiTiet_HoaDon> getAllChiTietHoaDons() {
        List<ChiTiet_HoaDon> chiTietList = new ArrayList<>();
        String query = "SELECT MaCTHD, MaHD, MaMon, SoLuong FROM ChiTiet_HoaDon";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String maCTHD = rs.getString("MaCTHD");
                String maHD = rs.getString("MaHD");
                String maMon = rs.getString("MaMon");
                int soLuong = rs.getInt("SoLuong");
                
                HoaDon hd = new HoaDon(maHD); // You would need to implement retrieving full HoaDon details
                MonAn mon = new MonAn(maMon); // You would need to implement retrieving full MonAn details
                
                ChiTiet_HoaDon chiTiet = new ChiTiet_HoaDon(maCTHD, hd, mon, soLuong);
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all ChiTiet_HoaDons: " + e.getMessage());
        }

        return chiTietList;
    }

    public void updateChiTietHoaDon(ChiTiet_HoaDon chiTiet) {
        String query = "UPDATE ChiTiet_HoaDon SET MaHD = ?, MaMon = ?, SoLuong = ? WHERE MaCTHD = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getHd().getMaHD());
            stmt.setString(2, chiTiet.getMon().getMaMon());
            stmt.setInt(3, chiTiet.getSoLuong());
            stmt.setString(4, chiTiet.getMaCTHD());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating ChiTiet_HoaDon: " + e.getMessage());
        }
    }
    public void deleteChiTietHoaDon(String maCTHD) {
        String query = "DELETE FROM ChiTiet_HoaDon WHERE MaCTHD = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maCTHD); // Specify which ChiTiet_HoaDon to delete by its ID
            stmt.executeUpdate(); // Execute the delete command
        } catch (SQLException e) {
            System.err.println("Error deleting ChiTiet_HoaDon: " + e.getMessage());
        }
    }
}