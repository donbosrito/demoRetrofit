package com.application.don.demoretrofit.View;

import com.application.don.demoretrofit.Model.SinhVien;

import java.util.List;

/**
 * Created by don on 11/30/16.
 */

public interface SinhVienView {
    void reload();

    void showNoData();

    void showError();

    void displayAllSinhVien(List<SinhVien> lstSinhVien);

    void displaySinhVien(SinhVien sinhVien);
}
