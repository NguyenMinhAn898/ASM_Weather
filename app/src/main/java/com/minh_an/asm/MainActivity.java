package com.minh_an.asm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.minh_an.asm.adapter.HomeAdapter;
import com.minh_an.asm.model.Item;
import com.minh_an.asm.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvIconPhrase,tvTemperature;
    RecyclerView recyclerView;
    List<Item> list;
    HomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvIconPhrase = findViewById(R.id.tvIconPhrase);
        tvTemperature = findViewById(R.id.tvTemperature);

        //B1 : get listdata
        getListData();

        //B2:
        list = new ArrayList<>();
        adapter = new HomeAdapter(this,list);

        //B3:
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        //B4:
        recyclerView = findViewById(R.id.rvListTemperature);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void getListData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.GetListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.body() != null){
                    list = response.body();
                    fistData(list.get(0));
                    adapter.reloadData(list);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }

    public void fistData(Item data){
        if(data != null){
            String temperature = data.getTemperature().getValue().toString() + " °C";
            tvTemperature.setText(temperature);
            tvIconPhrase.setText(data.getIconPhrase());
        }
    }
}