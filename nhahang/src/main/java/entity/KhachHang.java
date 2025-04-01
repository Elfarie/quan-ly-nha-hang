/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class KhachHang {
    private String MaKH;
    private String TenKH;
    private String SoDienTHoai;

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSoDienTHoai() {
        return SoDienTHoai;
    }

    public void setSoDienTHoai(String SoDienTHoai) {
        this.SoDienTHoai = SoDienTHoai;
    }

    

    public KhachHang(String MaKH, String TenKH, String SoDienTHoai) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SoDienTHoai = SoDienTHoai;
    }

    public KhachHang(String MaKH) {
        this.MaKH = MaKH;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.MaKH);
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
        final KhachHang other = (KhachHang) obj;
        return Objects.equals(this.MaKH, other.MaKH);
    }

    @Override
    public String toString() {
        return "KhachHang{" + "MaKH=" + MaKH + ", TenKH=" + TenKH + ", SoDienTHoai=" + SoDienTHoai + '}';
    }
    
}
