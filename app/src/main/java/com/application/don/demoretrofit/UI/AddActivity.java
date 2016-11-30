package com.application.don.demoretrofit.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.R;
import com.application.don.demoretrofit.API.SinhVienAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText edtMSSV, edtTen, edtLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtMSSV = (EditText) findViewById(R.id.edtMSSV);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtLop = (EditText) findViewById(R.id.edtLop);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtLop.getText().toString().isEmpty() ||
                        edtTen.getText().toString().isEmpty() ||
                        edtLop.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    insertSinhVien();
                }
            }
        });
    }

    private void insertSinhVien() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                // Sử dụng GSON cho việc parse và maps JSON data tới Object
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SinhVienAPI sinhVienAPI = retrofit.create(SinhVienAPI.class);
        // Get all post and add call back to catch response
        Call<Boolean> callInsertSinhVien = sinhVienAPI.insertSinhVien(edtMSSV.getText().toString(),
                edtTen.getText().toString(),
                edtLop.getText().toString());
        callInsertSinhVien.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body())
                    finish();
                else
                    Toast.makeText(AddActivity.this, "Có lỗi xảy ra", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
