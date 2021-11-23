package ru.miracle.madmeditation.data.network;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.miracle.madmeditation.data.network.service.MadMeditationService;

public class MadMeditationApi extends Application {

    // Private fields
    private final MadMeditationService madMeditationService;

    public MadMeditationService getService() {
        return madMeditationService;
    }

    MadMeditationApi() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        madMeditationService = retrofit.create(MadMeditationService.class);

    }

    // Static fields
    private static MadMeditationApi instance = null;

    // Static functions
    public static MadMeditationApi getInstance()
    {
        if(instance==null)
        {
            instance = new MadMeditationApi();
        }
        return instance;
    }
}
