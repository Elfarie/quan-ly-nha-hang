package dao;

import connectDB.connectdata;
import entity.NhanVien;
import entity.TK_NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TK_NhanVien_DAO {
    private Connection connection;

    public TK_NhanVien_DAO() {
        connectdata conn = new connectdata();
        this.connection = conn.getConnection(); // Ensure connection is successfully established
    }

    /**
     * Adds a new TK_NhanVien to the database.
     *
     * @param tk_nhanVien The TK_NhanVien object to add.
     */
    public void addTK_NhanVien(TK_NhanVien tk_nhanVien) {
        String query = "INSERT INTO TK_NhanVien (MatKhauTK, MaNV) VALUES (?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tk_nhanVien.getMatKhauTK()); // Use the password directly
            stmt.setString(2, tk_nhanVien.getNv().getMaNV()); // Assuming NhanVien has getMaNV()
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding TK_NhanVien: " + e.getMessage());
        }
    }

    /**
     * Retrieves a TK_NhanVien from the database using MaNV.
     *
     * @param maNV The MaNV of the TK_NhanVien to retrieve.
     * @return The corresponding TK_NhanVien object, or null if not found.
     */
    public TK_NhanVien getTK_NhanVien(String maNV) {
        String query = "SELECT MatKhauTK, MaNV FROM TK_NhanVien WHERE MaNV = ?";
        TK_NhanVien tk_nhanVien = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien(maNV); // Creating NhanVien based on the available MaNV
                tk_nhanVien = new TK_NhanVien(nv, rs.getString("MatKhauTK"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving TK_NhanVien: " + e.getMessage());
        }

        return tk_nhanVien;
    }

    /**
     * Retrieves all TK_NhanVien entries from the database.
     *
     * @return A list of all TK_NhanVien objects.
     */
    public List<TK_NhanVien> getAllTK_NhanViens() {
        List<TK_NhanVien> tk_nhanViens = new ArrayList<>();
        String query = "SELECT MatKhauTK, MaNV FROM TK_NhanVien";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNV"));
                TK_NhanVien tk_nhanVien = new TK_NhanVien(nv, rs.getString("MatKhauTK"));
                tk_nhanViens.add(tk_nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all TK_NhanViens: " + e.getMessage());
        }

        return tk_nhanViens;
    }

    /**
     * Updates an existing TK_NhanVien in the database.
     *
     * @param tk_nhanVien The TK_NhanVien object with updated values.
     */
    public boolean updateTK_NhanVien(TK_NhanVien tk_nhanVien) {
        String query = "UPDATE TK_NhanVien SET MatKhauTK = ? WHERE MaNV = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tk_nhanVien.getMatKhauTK());
            stmt.setString(2, tk_nhanVien.getNv().getMaNV());
            stmt.executeUpdate();
        } catch (SQLException e) {
            return false;
            //System.err.println("Error updating TK_NhanVien: " + e.getMessage());
        }
        return true;
    }

    /**
     * Deletes a TK_NhanVien from the database.
     *
     * @param maNV The MaNV of the TK_NhanVien to delete.
     */
    public void deleteTK_NhanVien(String maNV) {
        String query = "DELETE FROM TK_NhanVien WHERE MaNV = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maNV);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("No TK_NhanVien found with MaNV: " + maNV);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting TK_NhanVien: " + e.getMessage());
        }
    }
}