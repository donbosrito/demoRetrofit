package com.application.don.demoretrofit.Model;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.application.don.demoretrofit.API.SinhVienAPI;
import com.application.don.demoretrofit.UI.AddActivity;
import com.application.don.demoretrofit.UI.MainActivity;
import com.application.don.demoretrofit.UI.SearchActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by don on 11/30/16.
 */

public class SinhVienHelper {
    private String ROOT_URL = "http://demoqlsinhvien.apphb.com/";
    private OnLoadSinhVienResult onLoadSinhVienResult;

    public void setOnLoadUserResult(OnLoadSinhVienResult onLoadSinhVienResult) {
        this.onLoadSinhVienResult = onLoadSinhVienResult;
    }

    public void getAllSinhVien() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SinhVienAPI sinhVienAPI = retrofit.create(SinhVienAPI.class);
        Call<List<SinhVien>> callGetSinhViens = sinhVienAPI.getAllSinhVien();
        callGetSinhViens.enqueue(new Callback<List<SinhVien>>() {
            @Override
            public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                onLoadSinhVienResult.onLoadGetAllSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                onLoadSinhVienResult.onLoadError();
            }
        });
    }

    public void insertSinhVien(String maSo, String ten, String lop) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SinhVienAPI sinhVienAPI = retrofit.create(SinhVienAPI.class);
        Call<Boolean> callInsertSinhVien = sinhVienAPI.insertSinhVien(maSo, ten, lop);
        callInsertSinhVien.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                onLoadSinhVienResult.onLoadInsertSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                onLoadSinhVienResult.onLoadError();
            }
        });
    }

    public void getSinhVienByMaSo(String maSo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SinhVienAPI sinhVienAPI = retrofit.create(SinhVienAPI.class);
        Call<SinhVien> callGetSinhVienByMaSo = sinhVienAPI.getSinhVienByMaSo(maSo);
        callGetSinhVienByMaSo.enqueue(new Callback<SinhVien>() {
            @Override
            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                onLoadSinhVienResult.onLoadGetSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SinhVien> call, Throwable t) {
                onLoadSinhVienResult.onLoadError();
            }
        });
    }
}
