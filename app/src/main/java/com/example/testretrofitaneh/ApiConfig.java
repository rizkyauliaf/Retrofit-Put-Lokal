package com.example.testretrofitaneh;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    public static ApiInterface getService(){

        //ini coba pake appimu sendiri
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.29/restserver/index.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(ApiInterface.class);
    }
}
