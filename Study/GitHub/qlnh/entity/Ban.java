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
public class Ban {
    private String MaBan;
    private Khu khu;
    private int SoLuongNguoi;
    private boolean TrangThai;
    private double DonGia;

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public Khu getKhu() {
        return khu;
    }

    public void setKhu(Khu khu) {
        this.khu = khu;
    }

    public int getSoLuongNguoi() {
        return SoLuongNguoi;
    }

    public void setSoLuongNguoi(int SoLuongNguoi) {
        this.SoLuongNguoi = SoLuongNguoi;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public Ban(String MaBan, Khu khu, int SoLuongNguoi, boolean TrangThai, double DonGia) {
        this.MaBan = MaBan;
        this.khu = khu;
        this.SoLuongNguoi = SoLuongNguoi;
        this.TrangThai = TrangThai;
        this.DonGia = DonGia;
    }

    public Ban(String MaBan) {
        this.MaBan = MaBan;
    }
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.MaBan);
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
        final Ban other = (Ban) obj;
        return Objects.equals(this.MaBan, other.MaBan);
    }

    @Override
    public String toString() {
        return "Ban{" + "MaBan=" + MaBan + ", khu=" + khu + ", SoLuongNguoi=" + SoLuongNguoi + ", TrangThai=" + TrangThai + ", DonGia=" + DonGia + '}';
    }
    
}
