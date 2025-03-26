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
public class MonAn {
    private String MaMon;
    private String TenMon;
    private double DonGia;

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public MonAn(String MaMon, String TenMon, double DonGia) {
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.DonGia = DonGia;
    }

    public MonAn(String MaMon) {
        this.MaMon = MaMon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.MaMon);
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
        final MonAn other = (MonAn) obj;
        return Objects.equals(this.MaMon, other.MaMon);
    }

    @Override
    public String toString() {
        return "MonAn{" + "MaMon=" + MaMon + ", TenMon=" + TenMon + ", DonGia=" + DonGia + '}';
    }
    
}
