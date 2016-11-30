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
import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    ImageButton btnSearch;
    EditText edtSearch;
    ProgressDialog loading;
    TextView txtMSSV, txtTen, txtLop;
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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSinhVienByMaSo();
            }
        });
    }

    public void getSinhVienByMaSo() {
        loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SinhVienAPI sinhVienAPI = retrofit.create(SinhVienAPI.class);
        Call<SinhVien> callGetSinhVienByMaSo = sinhVienAPI.getSinhVienByMaSo(edtSearch.getText().toString());
        callGetSinhVienByMaSo.enqueue(new Callback<SinhVien>() {
            @Override
            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                loading.dismiss();
                if (response.body() != null) {
                    txtMSSV.setText(response.body().getMaSo());
                    txtTen.setText(response.body().getTen());
                    txtLop.setText(response.body().getLop());
                }
                else
                    Toast.makeText(SearchActivity.this, "Không tìm thấy sinh viên này",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SinhVien> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(SearchActivity.this, t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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
