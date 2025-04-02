package entity;

import java.util.Objects;

public class ChiTiet_DatBan {
    private Ban ban;
    private HoaDon hd;

    public ChiTiet_DatBan(Ban ban, HoaDon hd) {
        this.ban = ban;
        this.hd = hd;
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
        return Objects.hash(ban, hd);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChiTiet_DatBan other = (ChiTiet_DatBan) obj;
        return Objects.equals(ban, other.ban) && Objects.equals(hd, other.hd);
    }

    @Override
    public String toString() {
        return "ChiTiet_PhieuDatBan{" + "ban=" + ban + ", hd=" + hd + '}';
    }
}