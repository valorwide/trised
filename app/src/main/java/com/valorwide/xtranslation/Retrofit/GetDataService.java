package com.valorwide.xtranslation.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("getresult")
    Call<List<SurahModel>> getAllSurah();

    @GET("getsurahdetails")
    Call<List<SurahDetailsModel>> getSurahDetails(@Query("id") String id);

    @GET("next_page")
    Call<Response> sendMessageForOthers(@Query("msg") String msg,@Query("page") String page);

    @GET("userauth")
    Call<UserLogin> userAUth();
}
