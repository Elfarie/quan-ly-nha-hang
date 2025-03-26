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
public class Khu {
    private String MaKhu;
    private String TenKhu;

    public String getMaKhu() {
        return MaKhu;
    }

    public void setMaKhu(String MaKhu) {
        this.MaKhu = MaKhu;
    }

    public String getTenKhu() {
        return TenKhu;
    }

    public void setTenKhu(String TenKhu) {
        this.TenKhu = TenKhu;
    }

    public Khu(String MaKhu, String TenKhu) {
        this.MaKhu = MaKhu;
        this.TenKhu = TenKhu;
    }

    public Khu(String MaKhu) {
        this.MaKhu = MaKhu;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.MaKhu);
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
        final Khu other = (Khu) obj;
        return Objects.equals(this.MaKhu, other.MaKhu);
    }

    @Override
    public String toString() {
        return "Khu{" + "MaKhu=" + MaKhu + ", TenKhu=" + TenKhu + '}';
    }
    
}
