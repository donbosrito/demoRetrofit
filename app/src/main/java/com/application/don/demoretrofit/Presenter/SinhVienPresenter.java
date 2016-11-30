package com.application.don.demoretrofit.Presenter;

import com.application.don.demoretrofit.Model.OnLoadSinhVienResult;
import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.Model.SinhVienHelper;
import com.application.don.demoretrofit.View.SinhVienView;

import java.util.List;

/**
 * Created by don on 11/30/16.
 */

public class SinhVienPresenter implements OnLoadSinhVienResult {
    private SinhVienHelper model;
    private SinhVienView view;

    public SinhVienPresenter(SinhVienView view, SinhVienHelper model) {
        this.view = view;
        this.model = model;
        this.model.setOnLoadUserResult(this);
    }

    public void getAllSinhVien() {
        view.reload();
        model.getAllSinhVien();
    }

    public void reloadAllSinhVien() {
        getAllSinhVien();
    }

    public void getSinhVienByMaSo(String maSo) {
        view.reload();
        model.getSinhVienByMaSo(maSo);
    }

    public void reloadSinhVien(String maSo) {
        getSinhVienByMaSo(maSo);
    }

    public void insertSinhVien(String maSo, String ten, String lop) {
        view.reload();
        model.insertSinhVien(maSo, ten, lop);
    }

    public void reloadInsertSinhVien(String maSo, String ten, String lop) {
        insertSinhVien(maSo, ten, lop);
    }

    @Override
    public void onLoadGetAllSuccess(List<SinhVien> lstSinhVien) {
        if (lstSinhVien.size() > 0)
            view.displayAllSinhVien(lstSinhVien);
        else view.showNoData();
    }

    @Override
    public void onLoadGetSuccess(SinhVien sinhVien) {
        if (sinhVien != null)
            view.displaySinhVien(sinhVien);
        else view.showNoData();
    }

    @Override
    public void onLoadInsertSuccess(Boolean isSuccess) {
        if (isSuccess)
            view.showNoData();
        else view.showError();
    }

    @Override
    public void onLoadError() {
        view.showError();
    }
}
