package com.carlosribeiro.newsxpressworld;


import com.carlosribeiro.newsxpressworld.NewsResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsApiService {
    @GET("api/v4/top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("country") String country,
            @Query("token") String apiKey
    );
}

