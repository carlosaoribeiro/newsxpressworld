package com.carlosribeiro.newsxpressworld;


import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.List;


public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<News>> newsList;
    private static final String API_KEY = "61a846e87d7d642fc86ea61f0cd6c175";


    public NewsViewModel() {
        newsList = new MutableLiveData<>();
        fetchNews();
    }


    public LiveData<List<News>> getNews() {
        return newsList;
    }


    private void fetchNews() {
        Log.d("API_REQUEST", "Fazendo requisição à API...");


        NewsRepository.getInstance().getTopHeadlines("us", "61a846e87d7d642fc86ea61f0cd6c175").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsList.setValue(response.body().getArticles());
                    Log.d("API_SUCCESS", "Recebemos " + response.body().getTotalResults() + " notícias.");
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API_ERROR", "Código: " + response.code() + " | Erro: " + errorBody);
                    } catch (Exception e) {
                        Log.e("API_ERROR", "Erro ao processar a resposta da API.");
                    }
                }
            }


            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Falha na requisição: " + t.getMessage());
            }
        });
    }
}

