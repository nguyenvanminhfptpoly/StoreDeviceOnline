package com.minhnv.admin.storedeviceonline.model;

import java.io.Serializable;

public class Loaisp implements Serializable {
    public int id;
    public String TenloaiSP;
    public String HinhAnhSP;

    public Loaisp(int id, String tenloaiSP, String hinhAnhSP) {
        this.id = id;
        TenloaiSP = tenloaiSP;
        HinhAnhSP = hinhAnhSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaiSP() {
        return TenloaiSP;
    }

    public void setTenloaiSP(String tenloaiSP) {
        TenloaiSP = tenloaiSP;
    }

    public String getHinhAnhSP() {
        return HinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        HinhAnhSP = hinhAnhSP;
    }
}
