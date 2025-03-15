package com.carlosribeiro.newsxpressworld;


import java.io.Serializable;


public class News implements Serializable {
    private String title;
    private String description;
    private String url;
    private String image;


    public News(String title, String description, String url, String urlToImage) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = urlToImage;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getUrl() {
        return url;
    }


    public String getImage() {
        return image;
    }
}

