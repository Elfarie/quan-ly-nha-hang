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
public class TK_NhanVien {
    private NhanVien nv;
    private String MatKhauTK;
    private String MaTK;

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public String getMatKhauTK() {
        return MatKhauTK;
    }

    public void setMatKhauTK(String MatKhauTK) {
        this.MatKhauTK = MatKhauTK;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public TK_NhanVien(NhanVien nv, String MatKhauTK, String MaTK) {
        this.nv = nv;
        this.MatKhauTK = MatKhauTK;
        this.MaTK = MaTK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.MaTK);
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
        final TK_NhanVien other = (TK_NhanVien) obj;
        return Objects.equals(this.MaTK, other.MaTK);
    }

    @Override
    public String toString() {
        return "TK_NhanVien{" + "nv=" + nv + ", MatKhauTK=" + MatKhauTK + ", MaTK=" + MaTK + '}';
    }

}
