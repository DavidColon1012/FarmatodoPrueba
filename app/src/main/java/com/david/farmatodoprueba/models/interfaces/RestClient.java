package com.david.farmatodoprueba.models.interfaces;

import com.david.farmatodoprueba.models.MarvelFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {
    @GET("characters")
    Call<MarvelFeed> getCharacters();

    @GET("comics")
    Call<MarvelFeed> getComics();

    @GET("creators")
    Call<MarvelFeed> getCreators();

    @GET("events")
    Call<MarvelFeed> getEvents();

    @GET("series")
    Call<MarvelFeed> getSeries();

    @GET("stories")
    Call<MarvelFeed> getStories();

}