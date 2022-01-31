package com.example.Tazakhabar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tazakhabar2.R;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);





        getData();



    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsdata.io/api/1/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<News> call = newsApi.getData();
call.enqueue(new Callback<News>() {
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
        if(!response.isSuccessful()){
            Toast.makeText(MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
        }
       News news1=response.body();
        if(news1 != null){
            List<Resultarray> resultarrays=news1.getResults() ;
            for (int i=0; i<resultarrays.size(); i++) {
recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
Myadapter ad=new Myadapter(resultarrays,MainActivity.this);
ad.notifyDataSetChanged();

recyclerView.setAdapter(ad);
            }
        }


    }




    @Override
    public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
Log.d("sahil",""+t.getCause());
    }
});
    }


}