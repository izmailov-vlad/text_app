package ru.miracle.madmeditation.data.network.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.miracle.madmeditation.data.models.CatResponse;

public interface
CatGeneratorService {
    @GET("cat")
    Single<CatResponse> getCat();
}
