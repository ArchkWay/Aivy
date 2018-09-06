package com.example.archek.aivytask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("ticker/?limit=10&sort=id&format=json")
    Call<ListResponse> getCC();

}
