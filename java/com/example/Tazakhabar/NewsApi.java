package com.example.Tazakhabar;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    @GET("news?apikey=pub_3519be124615eab176fafe27a2f96e2cdfc2&country=in&language=en")
     Call<News> getData();

    }

