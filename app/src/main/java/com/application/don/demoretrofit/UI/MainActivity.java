package com.application.don.demoretrofit.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.don.demoretrofit.Adapter.SinhVienAdapter;
import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.Model.SinhVienHelper;
import com.application.don.demoretrofit.Presenter.SinhVienPresenter;
import com.application.don.demoretrofit.R;
import com.application.don.demoretrofit.API.SinhVienAPI;
import com.application.don.demoretrofit.View.SinhVienView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements SinhVienView {
    private ListView lsvSinhVien;
    private TextView txtMessage;


    ProgressDialog loading;

    private SinhVienPresenter sinhVienPresenter;
    private SinhVienHelper sinhVienHelper;
    SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lsvSinhVien = (ListView) findViewById(R.id.lsvSinhVien);
        txtMessage = (TextView) findViewById(R.id.txtMessage);

        if (sinhVienPresenter == null) {
            if (sinhVienHelper == null)
                sinhVienHelper = new SinhVienHelper();
            sinhVienPresenter = new SinhVienPresenter(this, sinhVienHelper);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sinhVienPresenter != null)
            sinhVienPresenter.getAllSinhVien();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sinhVienPresenter = null;
        sinhVienHelper = null;
        adapter = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mniAdd) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.mniSearch) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reload() {
        loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
    }

    @Override
    public void showNoData() {
        loading.dismiss();
        txtMessage.setText("Dữ liệu trống");
        txtMessage.setVisibility(View.VISIBLE);
        lsvSinhVien.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        loading.dismiss();
        txtMessage.setText("Có lỗi xảy ra");
        txtMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayAllSinhVien(List<SinhVien> lstSinhVien) {
        loading.dismiss();
        adapter = new SinhVienAdapter(this, lstSinhVien);
        lsvSinhVien.setAdapter(adapter);
        lsvSinhVien.setVisibility(View.VISIBLE);
        txtMessage.setVisibility(View.GONE);
    }

    @Override
    public void displaySinhVien(SinhVien sinhVien) {
    }
}
