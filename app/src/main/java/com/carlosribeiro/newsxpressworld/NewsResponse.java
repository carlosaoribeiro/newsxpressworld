package com.carlosribeiro.newsxpressworld;


import java.util.List;


public class NewsResponse {
    private String status;
    private int totalResults;
    private List<News> articles;


    public String getStatus() {
        return status;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public List<News> getArticles() {
        return articles;
    }
}

