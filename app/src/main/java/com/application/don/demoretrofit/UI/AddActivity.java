package com.application.don.demoretrofit.UI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.don.demoretrofit.Adapter.SinhVienAdapter;
import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.Model.SinhVienHelper;
import com.application.don.demoretrofit.Presenter.SinhVienPresenter;
import com.application.don.demoretrofit.R;
import com.application.don.demoretrofit.API.SinhVienAPI;
import com.application.don.demoretrofit.View.SinhVienView;

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


public class AddActivity extends AppCompatActivity implements SinhVienView {

    Button btnAdd, btnCancel;
    EditText edtMSSV, edtTen, edtLop;

    ProgressDialog loading;

    private SinhVienPresenter sinhVienPresenter;
    private SinhVienHelper sinhVienHelper;
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

        if (sinhVienPresenter == null) {
            if (sinhVienHelper == null)
                sinhVienHelper = new SinhVienHelper();
            sinhVienPresenter = new SinhVienPresenter(this, sinhVienHelper);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtLop.getText().toString().isEmpty() ||
                        edtTen.getText().toString().isEmpty() ||
                        edtLop.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (sinhVienPresenter != null)
                        sinhVienPresenter.insertSinhVien(edtMSSV.getText().toString(),
                                edtTen.getText().toString(),
                                edtLop.getText().toString());
                }
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

    @Override
    public void reload() {
        loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
    }

    @Override
    public void showNoData() {
        loading.dismiss();
        Toast.makeText(this, "Thêm Sinh viên thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError() {
        loading.dismiss();
        Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayAllSinhVien(List<SinhVien> lstSinhVien) {
    }

    @Override
    public void displaySinhVien(SinhVien sinhVien) {
    }
}
