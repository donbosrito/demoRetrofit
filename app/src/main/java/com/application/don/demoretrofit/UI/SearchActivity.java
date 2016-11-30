package com.application.don.demoretrofit.UI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.application.don.demoretrofit.API.SinhVienAPI;
import com.application.don.demoretrofit.Adapter.SinhVienAdapter;
import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.Model.SinhVienHelper;
import com.application.don.demoretrofit.Presenter.SinhVienPresenter;
import com.application.don.demoretrofit.R;
import com.application.don.demoretrofit.View.SinhVienView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements SinhVienView {

    ImageButton btnSearch;
    EditText edtSearch;
    ProgressDialog loading;
    TextView txtMSSV, txtTen, txtLop;

    private SinhVienPresenter sinhVienPresenter;
    private SinhVienHelper sinhVienHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        txtMSSV = (TextView) findViewById(R.id.txtMSSV);
        txtTen = (TextView) findViewById(R.id.txtTen);
        txtLop = (TextView) findViewById(R.id.txtLop);


        if (sinhVienPresenter == null) {
            if (sinhVienHelper == null)
                sinhVienHelper = new SinhVienHelper();
            sinhVienPresenter = new SinhVienPresenter(this, sinhVienHelper);
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sinhVienPresenter != null)
                    sinhVienPresenter.getSinhVienByMaSo(edtSearch.getText().toString());
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
        Toast.makeText(this, "Không tìm thấy sinh viên này", Toast.LENGTH_SHORT).show();
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
        loading.dismiss();
        txtMSSV.setText(sinhVien.getMaSo());
        txtTen.setText(sinhVien.getTen());
        txtLop.setText(sinhVien.getLop());
    }
}
