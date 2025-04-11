/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.ChiTiet_HoaDon_DAO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private String MaHD;
    private NhanVien nv;
    private KhachHang kh;
    private double Thue;
    private LocalDateTime NgayLapHD;

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public double getThue() {
        return Thue;
    }

    public void setThue(double Thue) {
        this.Thue = Thue;
    }

    public LocalDateTime getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(LocalDateTime NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }
    public double tinhTongTien(HoaDon hoaDon) {
        ChiTiet_HoaDon_DAO dao = new ChiTiet_HoaDon_DAO();
        List<ChiTiet_HoaDon> dsChiTiet = dao.getAllChiTietHoaDons(); // Lấy tất cả chi tiết

        double tongTien = 0;

        for (ChiTiet_HoaDon ct : dsChiTiet) {
            if (ct.getHd().getMaHD().equals(hoaDon.getMaHD())) {
                tongTien += ct.tinhThanhTien(); // sử dụng phương thức đã thêm
            }
        }

        // Cộng thêm thuế (nếu có)
        tongTien += tongTien * hoaDon.getThue();
        
        return tongTien;
    }

    public HoaDon(String MaHD, NhanVien nv, KhachHang kh, double Thue, LocalDateTime NgayLapHD) {
        this.MaHD = MaHD;
        this.nv = nv;
        this.kh = kh;
        this.Thue = Thue;
        this.NgayLapHD = NgayLapHD;
    }

    public HoaDon(String MaHD) {
        this.MaHD = MaHD;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.MaHD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.MaHD, other.MaHD);
    }

    @Override
    public String toString() {
        return "HoaDon{" + "MaHD=" + MaHD + ", nv=" + nv + ", kh=" + kh + ", Thue=" + Thue + ", NgayLapHD=" + NgayLapHD + '}';
    }
    
}
