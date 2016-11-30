package com.application.don.demoretrofit.Model;

/**
 * Created by don on 11/28/16.
 */

public class SinhVien {
    private String maSo;
    private String ten;
    private String lop;

    public SinhVien() {
    }

    public SinhVien(String maSo, String ten, String lop) {
        this.maSo = maSo;
        this.ten = ten;
        this.lop = lop;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
