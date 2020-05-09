package com.example.testretrofitaneh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {
    private TextView TextIdSiswa;
    private EditText EditNama, EditAlamat, EditTelp;
    private RadioGroup GroupJk;
    private RadioButton perempuan, laki;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //dilakukan proses findViewById untuk form pada activity_edit
        TextIdSiswa = findViewById(R.id.txt_id_siswa);
        EditNama = findViewById(R.id.edt_nama_1);
        EditAlamat = findViewById(R.id.edt_alamat_1);
        GroupJk = findViewById(R.id.group_jk_1);
        perempuan = findViewById(R.id.btn_perempuan_1);
        laki = findViewById(R.id.btn_laki_1);
        EditTelp = findViewById(R.id.edt_telp_1);

        //disini kita mengambil semua data yang dikirimin
        MahasiswaResponseModel mhsToEdit = getIntent().getParcelableExtra("mhs");

        //kita mengambil data yang didapatkan dari intent
        TextIdSiswa.setText(mhsToEdit.getId_siswa());
        EditNama.setText(mhsToEdit.getNama());
        EditAlamat.setText(mhsToEdit.getAlamat());
        if (mhsToEdit.getJenis_kelamin().equals("Perempuan")) {
            perempuan.setChecked(true);
        } else {
            laki.setChecked(true);
        }
        EditTelp.setText(mhsToEdit.getNo_telp());

        //kemudian disini terdapat onclicklistener jika button edit ditekan
        edit = findViewById(R.id.btn_edit_data);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mendapatkan data yang diisi lewat form
                String id_siswa = TextIdSiswa.getText().toString();
                String nama = EditNama.getText().toString();
                String alamat = EditAlamat.getText().toString();
                RadioButton selected = findViewById(GroupJk.getCheckedRadioButtonId());
                String jenis_kelamin = "";
                if (selected != null) {
                    jenis_kelamin = selected.getText().toString();
                }
                String no_telp = EditTelp.getText().toString();

                //ini kode jika data yang diisi kosong
                if (TextUtils.isEmpty(id_siswa) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(jenis_kelamin) || TextUtils.isEmpty(no_telp)) {
                    Toast.makeText(getApplicationContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    //instansiasi object dari konstruktor class Post
//                    MahasiswaResponseModel post = new MahasiswaResponseModel(id_siswa, nama, alamat, jenis_kelamin, no_telp);

                    //kemudian memanggil method editPost dengan parameter objek
                    Call<Void> call = ApiConfig.getService().editPost(
                            id_siswa,
                            nama,
                            alamat,
                            jenis_kelamin,
                            no_telp
                    );

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            //jika succes maka akan kehalaman mainActivity
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(EditActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}
