package com.shakiba.xtranslation.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetDataService {

    @GET("getresult")
    Call<List<SurahModel>> getAllSurah();
}
