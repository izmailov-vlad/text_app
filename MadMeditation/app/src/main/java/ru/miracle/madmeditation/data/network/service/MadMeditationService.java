package ru.miracle.madmeditation.data.network.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.QuotesResponse;


public interface MadMeditationService {
    @GET("quotes")
    Single<QuotesResponse> getQuotes();

    @GET("feelings")
    Single<FeelingsResponse> getFeelings();
}
