package dao;

import connectDB.connectdata;
import entity.ChiTiet_HoaDon;
import entity.Ban; // Assuming you have the Ban entity
import entity.HoaDon; // Assuming you have the HoaDon entity
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
        String query = "INSERT INTO ChiTiet_HoaDon (MaCTPhieu, MaHD, MaBan) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getMaCTPhieu());
            stmt.setString(2, chiTiet.getHd().getMaHD()); // Assuming HoaDon has a getMaHD method
            stmt.setString(3, chiTiet.getBan().getMaBan()); // Assuming Ban has a getMaBan method
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding ChiTiet_HoaDon: " + e.getMessage());
        }
    }

    public ChiTiet_HoaDon getChiTietHoaDon(String maCTPhieu) {
        String query = "SELECT MaCTPhieu, MaHD, MaBan FROM ChiTiet_HoaDon WHERE MaCTPhieu = ?";
        ChiTiet_HoaDon chiTiet = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maCTPhieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maHD = rs.getString("MaHD"); // Get HoaDon ID
                String maBan = rs.getString("MaBan"); // Get Ban ID

                HoaDon hd = new HoaDon(maHD); // You would retrieve full HoaDon details
                Ban ban = new Ban(maBan); // You would retrieve full Ban details
                
                chiTiet = new ChiTiet_HoaDon(hd, maCTPhieu, ban);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving ChiTiet_HoaDon: " + e.getMessage());
        }

        return chiTiet;
    }

    public List<ChiTiet_HoaDon> getAllChiTietHoaDons() {
        List<ChiTiet_HoaDon> chiTietList = new ArrayList<>();
        String query = "SELECT MaCTPhieu, MaHD, MaBan FROM ChiTiet_HoaDon";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String maCTPhieu = rs.getString("MaCTPhieu");
                String maHD = rs.getString("MaHD");
                String maBan = rs.getString("MaBan");

                HoaDon hd = new HoaDon(maHD); // Retrieve full HoaDon details
                Ban ban = new Ban(maBan); // Retrieve full Ban details
                
                ChiTiet_HoaDon chiTiet = new ChiTiet_HoaDon(hd, maCTPhieu, ban);
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all ChiTiet_HoaDons: " + e.getMessage());
        }

        return chiTietList;
    }

    public void updateChiTietHoaDon(ChiTiet_HoaDon chiTiet) {
        String query = "UPDATE ChiTiet_HoaDon SET MaHD = ?, MaBan = ? WHERE MaCTPhieu = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getHd().getMaHD());
            stmt.setString(2, chiTiet.getBan().getMaBan());
            stmt.setString(3, chiTiet.getMaCTPhieu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating ChiTiet_HoaDon: " + e.getMessage());
        }
    }

    public void deleteChiTietHoaDon(String maCTPhieu) {
        String query = "DELETE FROM ChiTiet_HoaDon WHERE MaCTPhieu = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maCTPhieu); // Specify which ChiTiet_HoaDon to delete by its ID
            stmt.executeUpdate(); // Execute the delete operation
        } catch (SQLException e) {
            System.err.println("Error deleting ChiTiet_HoaDon: " + e.getMessage());
        }
    }
}