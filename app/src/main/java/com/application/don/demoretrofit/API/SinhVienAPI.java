package com.application.don.demoretrofit.API;

import com.application.don.demoretrofit.Model.SinhVien;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by don on 11/28/16.
 */

public interface SinhVienAPI {
    @GET("/api/sinhvien")
    Call<List<SinhVien>> getAllSinhVien();

    @GET("/api/sinhvien/{maSo}")
    Call<SinhVien> getSinhVienByMaSo(@Path("maSo") String maSo);

    @POST("/api/sinhvien")
    Call<Boolean> insertSinhVien(@Query("maSo") String maSo,
                                      @Query("ten") String ten,
                                      @Query("lop") String lop);
}
