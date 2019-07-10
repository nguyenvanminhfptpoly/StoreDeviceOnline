package com.minhnv.admin.storedeviceonline.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private int id,GiaSP, idSP;
    private String TenSP, MotaSP, HinhanhSP;

    public Sanpham(int id, int giaSP, int idSP, String tenSP, String motaSP, String hinhanhSP) {
        this.id = id;
        this.GiaSP = giaSP;
        this.idSP = idSP;
        this.TenSP = tenSP;
        this.MotaSP = motaSP;
        this.HinhanhSP = hinhanhSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getMotaSP() {
        return MotaSP;
    }

    public void setMotaSP(String motaSP) {
        MotaSP = motaSP;
    }

    public String getHinhanhSP() {
        return HinhanhSP;
    }

    public void setHinhanhSP(String hinhanhSP) {
        HinhanhSP = hinhanhSP;
    }
}
