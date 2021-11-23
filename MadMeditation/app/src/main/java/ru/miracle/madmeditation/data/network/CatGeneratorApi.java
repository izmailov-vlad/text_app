package ru.miracle.madmeditation.data.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.miracle.madmeditation.data.network.service.CatGeneratorService;
import ru.miracle.madmeditation.data.network.service.MadMeditationService;

public class CatGeneratorApi {
    private final CatGeneratorService catGeneratorService;

    public CatGeneratorService getService() {
        return catGeneratorService;
    }

    public CatGeneratorApi() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        catGeneratorService = retrofit.create(CatGeneratorService.class);
    }

    // Static fields
    private static CatGeneratorApi instance = null;

    // Static functions
    public static CatGeneratorApi getInstance()
    {
        if(instance==null)
        {
            instance = new CatGeneratorApi();
        }
        return instance;
    }

    private final static String BASE_URL = "https://cat-generator-api.herokuapp.com/";
}
