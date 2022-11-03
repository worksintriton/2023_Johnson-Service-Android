package com.triton.johnson_tap_app;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {

//    String JSONURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";
//
//    @GET("json_parsing.php")
//    Call<String> getString();

    String JSONURL = "http://smart.johnsonliftsltd.com:3000/api/activity/";

    @GET("form3_rtgs_list")
    Call<String> getString();
}
