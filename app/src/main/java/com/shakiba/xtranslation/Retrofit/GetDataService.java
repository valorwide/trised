package com.shakiba.xtranslation.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("getresult")
    Call<Void> getAllSurah();
}
