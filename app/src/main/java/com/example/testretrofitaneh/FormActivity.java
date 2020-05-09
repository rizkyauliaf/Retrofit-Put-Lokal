package com.example.testretrofitaneh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormActivity extends AppCompatActivity {
    //terdapat pendeklarasian id,nama,alamat, no telp
    private EditText inputid_siswa, inputNama, inputAlamat, inputTelp;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button btn = findViewById(R.id.btn_tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kita arahkan sesuai dengan id, nama, alamat, dan no telpon
                inputid_siswa = findViewById(R.id.edt_nim);
                inputNama = findViewById(R.id.edt_nama);
                inputAlamat = findViewById(R.id.edt_alamat);
                inputTelp = findViewById(R.id.edt_telp);

                //ini variabel untuk menyimpan data yang didapat dari form-nya
                String nim = inputid_siswa.getText().toString();
                String nama = inputNama.getText().toString();
                String alamat = inputAlamat.getText().toString();
                radioGroup = findViewById(R.id.group_jk);
                RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
                String jenis_kelamin = "";
                if (selected.getText().toString().equals("Perempuan")) {
                    jenis_kelamin = "P";
                } else if ((selected.getText().toString().equals("Laki-laki"))) {
                    jenis_kelamin = "L";
                }
                String no_telp = inputTelp.getText().toString();

//                TextView txt = findViewById(R.id.text_title);
//                txt.setText(nim+nama+alamat+jenis_kelamin+no_telp);

                //ini kode jika data yang diisi kosong
                if (TextUtils.isEmpty(nim) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(jenis_kelamin) || TextUtils.isEmpty(no_telp)) {
                    Toast.makeText(getApplicationContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
//                    MahasiswaResponseModel post = new MahasiswaResponseModel(nim, nama, alamat, jenis_kelamin, no_telp);


                    //lalu kita panggil method setpost
                    //kemudian mengisi parameternya ke body sesuai data yang dibutuhkan yakni

                    Call<Void> call = ApiConfig.getService().setPost(
                            nim,
                            nama,
                            alamat,
                            jenis_kelamin,
                            no_telp
                    );

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(FormActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
//udah selesai semog a bisaaaa yaaaa :)
}

