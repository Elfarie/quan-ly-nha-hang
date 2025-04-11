package entity;

import java.util.Objects;

public class ChiTiet_HoaDon {
    private HoaDon hd;
    private MonAn mon;
    private int soLuong;
    private double donGia; // <--- Thêm dòng này

    public ChiTiet_HoaDon(HoaDon hd, MonAn mon, int soLuong, double donGia) {
        this.hd = hd;
        this.mon = mon;
        this.soLuong = soLuong;
        this.donGia = donGia;
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.hd);
        hash = 97 * hash + Objects.hashCode(this.mon);
        hash = 97 * hash + this.soLuong;
        hash = 97 * hash + Double.hashCode(this.donGia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final ChiTiet_HoaDon other = (ChiTiet_HoaDon) obj;
        return this.soLuong == other.soLuong
            && Double.compare(this.donGia, other.donGia) == 0
            && Objects.equals(this.hd, other.hd)
            && Objects.equals(this.mon, other.mon);
    }

    @Override
    public String toString() {
        return "ChiTiet_HoaDon{" +
                "hd=" + hd +
                ", mon=" + mon +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", thanhTien=" + tinhThanhTien() +
                '}';
    }
}
