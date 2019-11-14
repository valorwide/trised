package com.shakiba.xtranslation.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("getresult")
    Call<List<SurahModel>> getAllSurah();

    @GET("getsurahdetails")
    Call<List<SurahDetailsModel>> getSurahDetails(@Query("id") String id);
@FormUrlEncoded
    @POST("next_page")
    Call<Response> sendMessageForOthers(@Field("msg") String msg,@Field("page") String page);
}
