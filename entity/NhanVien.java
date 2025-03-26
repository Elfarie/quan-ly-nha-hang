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
public class NhanVien {
    private String MaNV;
    private String TenNV;
    private int SoDienTHoai;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public int getSoDienTHoai() {
        return SoDienTHoai;
    }

    public void setSoDienTHoai(int SoDienTHoai) {
        this.SoDienTHoai = SoDienTHoai;
    }

    public NhanVien(String MaNV, String TenNV, int SoDienTHoai) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.SoDienTHoai = SoDienTHoai;
    }

    public NhanVien(String MaNV) {
        this.MaNV = MaNV;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.MaNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.MaNV, other.MaNV);
    }
    
    
    @Override
    public String toString() {
        return "NhanVien{" + "MaNV=" + MaNV + ", TenNV=" + TenNV + ", SoDienTHoai=" + SoDienTHoai + '}';
    }
    
}
