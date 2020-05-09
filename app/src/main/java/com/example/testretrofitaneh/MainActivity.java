package com.example.testretrofitaneh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MahasiswaAdapter.OnClickListener {

    private TextView textViewResult;


    //terus di mainactivity kudu implementasi interfacenya tadi untuk menambahkan logicnya pas diklick itu ngappain
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.judul);


        final RecyclerView siswaView = findViewById(R.id.rv_siswa);
        final List siswa = new ArrayList<>();
        final MahasiswaAdapter mahasiswaAdapter = new MahasiswaAdapter(this, siswa);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        siswaView.setLayoutManager(layoutManager);
        siswaView.setAdapter(mahasiswaAdapter);

        //ini karna retrofitnya udah aku bikinin kelas sendiri jadi enak gausah nulis terus, tinggal manggil ApiConfig.getService
        //hasilnya udah kayak ini,
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://apimahasiswayoga.000webhostapp.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //jadi manggilnya simple kek gini
        Call<List<MahasiswaResponseModel>> call = ApiConfig.getService().getAllMahasiwa();
        //lalu memanggil method getPost dari class JsonPlaceHolderApi untuk mengambil data dari rest server
        call.enqueue(new Callback<List<MahasiswaResponseModel>>() {
            @Override
            public void onResponse(Call<List<MahasiswaResponseModel>> call, Response<List<MahasiswaResponseModel>> response) {
                //ini adalah pengkondisian, kodisi pertama seperti ini
                if (!response.isSuccessful()){
                    textViewResult.setText("Code " + response.code());
                    return;
                }

                //siswa nya ditambah yang baru dari response
                siswa.addAll(response.body());
                //terus adapternya diberi tahu kalau ada peruban datanya
                mahasiswaAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<MahasiswaResponseModel>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    //menanbahkan myonclick ini digunakan untuk perpinadahan ke halaman pengisian data yakni formactivity
    public void myOnClick(View view){
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }

    //jadi ketika salah satu data diklik maka akan berpindah ke halaman ubah
    @Override
    public void onClick(MahasiswaResponseModel mhs) {
        Intent intent = new Intent(this, EditActivity.class);
        //ini bagian ngirimnya
        intent.putExtra("mhs", mhs);
        startActivity(intent);
    }
}
