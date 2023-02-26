package com.mhmmdyzci.retrofitjava.Service;

import com.mhmmdyzci.retrofitjava.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //GET(VERİYİ ALMAK İÇİN), POST(SUNUCUYA VERİ YAZMAK İÇİN), UPDATE, DELETE
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    // getdata() isim
    Call<List<CryptoModel>> getData();

}
