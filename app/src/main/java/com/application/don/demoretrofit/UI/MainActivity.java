package com.application.don.demoretrofit.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.application.don.demoretrofit.Adapter.SinhVienAdapter;
import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.R;
import com.application.don.demoretrofit.API.SinhVienAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://demoqlsinhvien.apphb.com/";

    private ListView lsvSinhVien;

    private List<SinhVien> lstSinhVien;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lsvSinhVien = (ListView) findViewById(R.id.lsvSinhVien);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllSinhVien();
    }

    public void getAllSinhVien() {
        loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                // Sử dụng GSON cho việc parse và maps JSON data tới Object
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SinhVienAPI sinhVienAPI = retrofit.create(SinhVienAPI.class);
        // Get all post and add call back to catch response
        Call<List<SinhVien>> callGetSinhViens = sinhVienAPI.getAllSinhVien();
        callGetSinhViens.enqueue(new Callback<List<SinhVien>>() {
            @Override
            public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                lstSinhVien = response.body();

                //Calling a method to show the list
                showList();
            }

            @Override
            public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
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
        }
        else if (id == R.id.mniSearch) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showList(){
        SinhVienAdapter adapter = new SinhVienAdapter(this, lstSinhVien);
        lsvSinhVien.setAdapter(adapter);
    }
}
