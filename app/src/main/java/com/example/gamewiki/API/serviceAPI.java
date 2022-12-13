package com.example.gamewiki.API;


import com.example.gamewiki.API.request.dataRequest;
import com.example.gamewiki.API.response.item;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface serviceAPI {
    String BASE_SERVICE = "https://valheim.onrender.com/";

    @GET("api/getAllItem")
    Observable<ArrayList<item>> getAllItem();

    @POST("api/getItemByName")
    Observable<ArrayList<item>> getItemByName(@Body dataRequest bodyRequest);

    @POST("api/getItemById")
    Observable<item> getItemById (@Body dataRequest bodyRequest);

    @POST("api/can-crafting")
    Observable<ArrayList<String>> canCrafting (@Body dataRequest bodyRequest);

    @POST("api/crafting")
    Observable<ArrayList<item>> crafting (@Body dataRequest bodyRequest);
}
