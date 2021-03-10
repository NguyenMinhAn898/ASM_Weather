package com.minh_an.asm.network;

import com.minh_an.asm.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String APIKEY ="wQ77JSlDmCs2WrjmRqmhA8bUetaeEl6Z";
    String SERVER ="http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/";
    String URI = "353412?apikey="+APIKEY+"&language=vi-vn&metric=true";
//    String SERVER_5DAY ="http://dataservice.accuweather.com/forecasts/v1/daily/5day/";
//    String URI_5DAY ="353412?apikey="+APIKEY+"&language=vi-vn&metric=true";
    @GET(URI)
    Call<List<Item>> GetListData();

//    @GET(URI_5DAY)
//    Call<> GetlistDataFiveDay();

}
