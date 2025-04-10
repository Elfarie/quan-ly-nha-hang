package entity;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class ChiTiet_HoaDon {
    private HoaDon hd;      // Reference to HoaDon entity
    private MonAn mon;      // Reference to MonAn entity
    private int soLuong;    // Quantity of the item

    public ChiTiet_HoaDon(HoaDon hd, MonAn mon, int soLuong) {
        this.hd = hd;
        this.mon = mon;
        this.soLuong = soLuong;
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.hd);
        hash = 97 * hash + Objects.hashCode(this.mon);
        hash = 97 * hash + this.soLuong;
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
        if (this.soLuong != other.soLuong) {
            return false;
        }
        if (!Objects.equals(this.hd, other.hd)) {
            return false;
        }
        return Objects.equals(this.mon, other.mon);
    }

    @Override
    public String toString() {
        return "ChiTiet_HoaDon{" + "hd=" + hd + ", mon=" + mon + ", soLuong=" + soLuong + '}';
    }

    
}