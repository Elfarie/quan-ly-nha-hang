package entity;

import java.util.Objects;

public class Ban {
    private String MaBan;
    private int SoLuongNguoi;
    private boolean TrangThai;

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
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

    public Ban(String MaBan, int SoLuongNguoi, boolean TrangThai) {
        this.MaBan = MaBan;
        this.SoLuongNguoi = SoLuongNguoi;
        this.TrangThai = TrangThai;
    }

    public Ban(String MaBan) {
        this.MaBan = MaBan;
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaBan);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ban other = (Ban) obj;
        return Objects.equals(this.MaBan, other.MaBan);
    }

    @Override
    public String toString() {
        return "Ban{" + "MaBan=" + MaBan + ", SoLuongNguoi=" + SoLuongNguoi + ", TrangThai=" + TrangThai + '}';
    }
}