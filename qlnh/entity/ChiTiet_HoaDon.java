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
public class ChiTiet_HoaDon {
    private HoaDon hd;
    private String MaCTPhieu;
    private Ban ban;

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

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

    public ChiTiet_HoaDon(HoaDon hd, String MaCTPhieu, Ban ban) {
        this.hd = hd;
        this.MaCTPhieu = MaCTPhieu;
        this.ban = ban;
    }

    public ChiTiet_HoaDon(String MaCTPhieu) {
        this.MaCTPhieu = MaCTPhieu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.MaCTPhieu);
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
        final ChiTiet_HoaDon other = (ChiTiet_HoaDon) obj;
        return Objects.equals(this.MaCTPhieu, other.MaCTPhieu);
    }

    @Override
    public String toString() {
        return "ChiTiet_HoaDon{" + "hd=" + hd + ", MaCTPhieu=" + MaCTPhieu + ", ban=" + ban + '}';
    }
    
}
