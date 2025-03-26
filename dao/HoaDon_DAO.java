package dao;

import connectDB.connectdata;
import entity.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HoaDon_DAO {
    private Connection connection;

    public HoaDon_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Assume connection is established
    }

    public void addHoaDon(HoaDon hoaDon) {
        String query = "INSERT INTO HoaDon (MaHD, MaNV, MaKH, Thue, NgayLapHD) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoaDon.getMaHD());
            stmt.setString(2, hoaDon.getNv().getMaNV()); // Assuming NhanVien has a getMaNV method
            stmt.setString(3, hoaDon.getKh().getMaKH()); // Assuming KhachHang has a getMaKH method
            stmt.setDouble(4, hoaDon.getThue());
            stmt.setTimestamp(5, Timestamp.valueOf(hoaDon.getNgayLapHD())); // Convert LocalDateTime to Timestamp
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding HoaDon: " + e.getMessage());
        }
    }

    public HoaDon getHoaDon(String maHD) {
        String query = "SELECT MaHD, MaNV, MaKH, Thue, NgayLapHD FROM HoaDon WHERE MaHD = ?";
        HoaDon hoaDon = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maNV = rs.getString("MaNV"); // Get NhanVien ID
                String maKH = rs.getString("MaKH"); // Get KhachHang ID
                double thue = rs.getDouble("Thue");
                LocalDateTime ngayLapHD = rs.getTimestamp("NgayLapHD").toLocalDateTime(); // Convert Timestamp to LocalDateTime
                
                // Assuming you have methods to get NhanVien and KhachHang objects
                NhanVien nv = new NhanVien(maNV); // You would retrieve full NhanVien details (depending on your implementation)
                KhachHang kh = new KhachHang(maKH); // You would retrieve full KhachHang details (depending on your implementation)

                hoaDon = new HoaDon(maHD, nv, kh, thue, ngayLapHD);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving HoaDon: " + e.getMessage());
        }

        return hoaDon;
    }

    public List<HoaDon> getAllHoaDons() {
        List<HoaDon> hoaDons = new ArrayList<>();
        String query = "SELECT MaHD, MaNV, MaKH, Thue, NgayLapHD FROM HoaDon";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maNV = rs.getString("MaNV");
                String maKH = rs.getString("MaKH");
                double thue = rs.getDouble("Thue");
                LocalDateTime ngayLapHD = rs.getTimestamp("NgayLapHD").toLocalDateTime();

                NhanVien nv = new NhanVien(maNV); // Retrieve full NhanVien details
                KhachHang kh = new KhachHang(maKH); // Retrieve full KhachHang details
                
                HoaDon hoaDon = new HoaDon(maHD, nv, kh, thue, ngayLapHD);
                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all HoaDons: " + e.getMessage());
        }

        return hoaDons;
    }

   public void updateHoaDon(HoaDon hoaDon) {
        String query = "UPDATE HoaDon SET MaNV = ?, MaKH = ?, Thue = ?, NgayLapHD = ? WHERE MaHD = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoaDon.getNv().getMaNV()); // Update NhanVien ID
            stmt.setString(2, hoaDon.getKh().getMaKH()); // Update KhachHang ID
            stmt.setDouble(3, hoaDon.getThue()); // Update tax
            stmt.setTimestamp(4, Timestamp.valueOf(hoaDon.getNgayLapHD())); // Convert LocalDateTime to Timestamp
            stmt.setString(5, hoaDon.getMaHD()); // Specify which HoaDon to update by its ID
            stmt.executeUpdate(); // Perform the update
        } catch (SQLException e) {
            System.err.println("Error updating HoaDon: " + e.getMessage());
        }
    }
}
 