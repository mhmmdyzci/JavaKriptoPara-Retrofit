package com.mhmmdyzci.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.Settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mhmmdyzci.retrofitjava.Adapter.RecyclerViewAdapter;
import com.mhmmdyzci.retrofitjava.R;
import com.mhmmdyzci.retrofitjava.Service.CryptoAPI;
import com.mhmmdyzci.retrofitjava.model.CryptoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL= "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        //Retrofit ve JSON
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();


    }
    private void loadData(){
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);
                    //RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();

            }
        });




    }
}




//https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json