package com.application.don.demoretrofit.Model;

import java.util.List;

/**
 * Created by don on 11/30/16.
 */

public interface OnLoadSinhVienResult {
    public void onLoadGetAllSuccess(List<SinhVien> lstSinhVien);

    public void onLoadGetSuccess(SinhVien lstSinhVien);

    public void onLoadInsertSuccess(Boolean isSuccess);

    public void onLoadError();
}
