package com.carlosribeiro.newsxpressworld;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsRepository {
    private static final String BASE_URL = "https://gnews.io/";
    private static NewsApiService newsApiService;


    public static NewsApiService getInstance() {
        if (newsApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            newsApiService = retrofit.create(NewsApiService.class);
        }
        return newsApiService;
    }
}

