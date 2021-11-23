package ru.miracle.madmeditation.data.repository.dashboard;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.QuotesResponse;
import ru.miracle.madmeditation.data.network.MadMeditationApi;
import ru.miracle.madmeditation.domain.repository.DashboardRepository;

public class DashboardRepositoryImpl implements DashboardRepository {

    private final MadMeditationApi madMeditationApi = MadMeditationApi.getInstance();

    @Override
    public Single<QuotesResponse> getQuotes() {
        return madMeditationApi.getService().getQuotes();
    }

    @Override
    public Single<FeelingsResponse> getFeelings() {
        return madMeditationApi.getService().getFeelings();
    }
}
