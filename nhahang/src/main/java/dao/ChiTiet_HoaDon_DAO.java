package dao;

import connectDB.connectdata;
import entity.ChiTiet_HoaDon;
import entity.HoaDon;
import entity.MonAn;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChiTiet_HoaDon_DAO {
    private Connection connection;

    public ChiTiet_HoaDon_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection();
    }

    public void addChiTietHoaDon(ChiTiet_HoaDon chiTiet) {
        String query = "INSERT INTO ChiTiet_HoaDon (MaHD, MaMon, SoLuong, DonGia) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chiTiet.getHd().getMaHD());
            stmt.setString(2, chiTiet.getMon().getMaMon());
            stmt.setInt(3, chiTiet.getSoLuong());
            stmt.setDouble(4, chiTiet.getDonGia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding ChiTiet_HoaDon: " + e.getMessage());
        }
    }
    public int getSoLuongMonAnDaBan(String maMonAn) {
    String query = "SELECT SUM(SoLuong) AS TongSoLuong FROM ChiTiet_HoaDon WHERE MaMon = ?";
    int tongSoLuong = 0;

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, maMonAn);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            tongSoLuong = rs.getInt("TongSoLuong");
        }
    } catch (SQLException e) {
        System.err.println("Error calculating total sold for MonAn: " + e.getMessage());
    }

    return tongSoLuong;
}
public List<ChiTiet_HoaDon> getChiTietHoaDonTheoMaHD(String maHD) {
    List<ChiTiet_HoaDon> list = new ArrayList<>();
    String sql = "SELECT MaMon, SoLuong, DonGia FROM ChiTiet_HoaDon WHERE MaHD = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, maHD);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String maMon = rs.getString("MaMon");
            int soLuong = rs.getInt("SoLuong");
            double donGia = rs.getDouble("DonGia");

            MonAn mon = new MonAn(maMon); // chỉ cần MaMon
            HoaDon hd = new HoaDon(maHD); // chỉ cần MaHD

            list.add(new ChiTiet_HoaDon(hd, mon, soLuong, donGia));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}



    public ChiTiet_HoaDon getChiTietHoaDon(String maHD, String maMon) {
        String query = "SELECT MaHD, MaMon, SoLuong, DonGia FROM ChiTiet_HoaDon WHERE MaHD = ? AND MaMon = ?";
        ChiTiet_HoaDon chiTiet = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHD);
            stmt.setString(2, maMon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");

                HoaDon hd = new HoaDon(maHD);
                MonAn mon = new MonAn(maMon);

                chiTiet = new ChiTiet_HoaDon(hd, mon, soLuong, donGia);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving ChiTiet_HoaDon: " + e.getMessage());
        }

        return chiTiet;
    }

    public List<ChiTiet_HoaDon> getAllChiTietHoaDons() {
        List<ChiTiet_HoaDon> chiTietList = new ArrayList<>();
        String query = "SELECT MaHD, MaMon, SoLuong, DonGia FROM ChiTiet_HoaDon";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maMon = rs.getString("MaMon");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");

                HoaDon hd = new HoaDon(maHD);
                MonAn mon = new MonAn(maMon);

                ChiTiet_HoaDon chiTiet = new ChiTiet_HoaDon(hd, mon, soLuong, donGia);
                chiTietList.add(chiTiet);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all ChiTiet_HoaDons: " + e.getMessage());
        }

        return chiTietList;
    }

    public void updateChiTietHoaDon(ChiTiet_HoaDon chiTiet) {
        String query = "UPDATE ChiTiet_HoaDon SET SoLuong = ?, DonGia = ? WHERE MaHD = ? AND MaMon = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, chiTiet.getSoLuong());
            stmt.setDouble(2, chiTiet.getDonGia());
            stmt.setString(3, chiTiet.getHd().getMaHD());
            stmt.setString(4, chiTiet.getMon().getMaMon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating ChiTiet_HoaDon: " + e.getMessage());
        }
    }
public int getSoLuongMonAnDaBanTrongKhoang(String maMon, LocalDateTime tuNgay, LocalDateTime denNgay) {
    int tongSoLuong = 0;

    String query = "SELECT SUM(SoLuong) as TongSoLuong FROM ChiTiet_HoaDon ct " +
                   "JOIN HoaDon hd ON ct.MaHD = hd.MaHD " +
                   "WHERE ct.MaMon = ? AND hd.NgayLap BETWEEN ? AND ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, maMon);
        stmt.setTimestamp(2, Timestamp.valueOf(tuNgay));
        stmt.setTimestamp(3, Timestamp.valueOf(denNgay));
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            tongSoLuong = rs.getInt("TongSoLuong");
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi thống kê theo thời gian: " + e.getMessage());
    }

    return tongSoLuong;
}
    public void deleteChiTietHoaDon(String maHD, String maMon) {
        String query = "DELETE FROM ChiTiet_HoaDon WHERE MaHD = ? AND MaMon = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHD);
            stmt.setString(2, maMon);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting ChiTiet_HoaDon: " + e.getMessage());
        }
    }
    
    }

    // Hiển thị thông báo nếu không có món nào được bán
