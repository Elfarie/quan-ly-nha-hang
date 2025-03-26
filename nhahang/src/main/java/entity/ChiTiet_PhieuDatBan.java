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
public class ChiTiet_PhieuDatBan {
    private String MaCTPhieu;
    private Ban ban;
    private HoaDon hd;

    public String getMaCTPhieu() {
        return MaCTPhieu;
    }

    public void setMaCTPhieu(String MaCTPhieu) {
        this.MaCTPhieu = MaCTPhieu;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.MaCTPhieu);
        return hash;
    }

    public ChiTiet_PhieuDatBan(String MaCTPhieu, Ban ban, HoaDon hd) {
        this.MaCTPhieu = MaCTPhieu;
        this.ban = ban;
        this.hd = hd;
    }

    public ChiTiet_PhieuDatBan(String MaCTPhieu) {
        this.MaCTPhieu = MaCTPhieu;
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
        final ChiTiet_PhieuDatBan other = (ChiTiet_PhieuDatBan) obj;
        return Objects.equals(this.MaCTPhieu, other.MaCTPhieu);
    }

    @Override
    public String toString() {
        return "ChiTiet_PhieuDatBan{" + "MaCTPhieu=" + MaCTPhieu + ", ban=" + ban + ", hd=" + hd + '}';
    }
    
    
}
