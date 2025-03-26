package entity;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class ChiTiet_HoaDon {
    private String MaCTHD; // Primary key
    private HoaDon hd;      // Reference to HoaDon entity
    private MonAn mon;      // Reference to MonAn entity
    private int soLuong;    // Quantity of the item

    public ChiTiet_HoaDon(String maCTHD, HoaDon hd, MonAn mon, int soLuong) {
        this.MaCTHD = maCTHD;
        this.hd = hd;
        this.mon = mon;
        this.soLuong = soLuong;
    }

    public String getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        MaCTHD = maCTHD;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    public MonAn getMon() {
        return mon;
    }

    public void setMon(MonAn mon) {
        this.mon = mon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaCTHD);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChiTiet_HoaDon other = (ChiTiet_HoaDon) obj;
        return Objects.equals(MaCTHD, other.MaCTHD);
    }

    @Override
    public String toString() {
        return "ChiTiet_HoaDon{" +
                "MaCTHD='" + MaCTHD + '\'' +
                ", hd=" + hd +
                ", mon=" + mon +
                ", soLuong=" + soLuong +
                '}';
    }
}