package com.david.farmatodoprueba.models.repositories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.david.farmatodoprueba.models.AuthInterceptor;
import com.david.farmatodoprueba.models.MarvelFeed;
import com.david.farmatodoprueba.models.interfaces.Callbacks;
import com.david.farmatodoprueba.models.interfaces.RestClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class MarvelRepository {

    private static MarvelRepository instance;
    private Gson gson;

    public static MarvelRepository getInstance() {
        if (instance == null) {
            instance = new MarvelRepository();
        }
        return instance;
    }

    private MarvelRepository() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }


    public void getListMarvel(Context mContext, int multiple, final Callbacks callbacks) {
        if (isOnline(mContext)) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor("d6bc942fe4a6a4f684fc53e54c5368b1", "3c5d0ca093b792a98aa9e6a65358d156e6d268dc"));

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);


            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://gateway.marvel.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();


            Call<MarvelFeed> call;
            RestClient restClient = retrofit.create(RestClient.class);

            switch (multiple) {
                case 0:
                    call = restClient.getCharacters();
                    break;
                case 3:
                case 5:
                    call = restClient.getComics();
                    break;
                case 7:
                    call = restClient.getCreators();
                    break;
                case 11:
                    call = restClient.getEvents();
                    break;
                case 13:
                    call = restClient.getSeries();
                    break;
                default:
                    call = restClient.getStories();
                    break;
            }

            call.enqueue(new Callback<MarvelFeed>() {
                @Override
                public void onResponse(Call<MarvelFeed> call, Response<MarvelFeed> response) {
                    switch (response.code()) {
                        case 200:
                            callbacks.onSuccess(response.body().getData().getResults());
                            break;
                        default:
                            callbacks.onFail("¡Hubo un inconveniente!, por favor inténtelo nuevamente.");
                    }
                }

                @Override
                public void onFailure(Call<MarvelFeed> call, Throwable t) {

                }
            });
        }else{
            callbacks.onFail("Sin conexión a internet");
        }
    }

    private static boolean isOnline(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
