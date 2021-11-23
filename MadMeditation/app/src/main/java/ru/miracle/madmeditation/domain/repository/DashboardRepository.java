package ru.miracle.madmeditation.domain.repository;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.QuotesResponse;

public interface DashboardRepository {
    Single<QuotesResponse> getQuotes();
    Single<FeelingsResponse> getFeelings();
}
