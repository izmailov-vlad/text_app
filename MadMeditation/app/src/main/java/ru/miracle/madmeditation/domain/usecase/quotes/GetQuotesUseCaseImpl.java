package ru.miracle.madmeditation.domain.usecase.quotes;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.QuotesResponse;
import ru.miracle.madmeditation.data.repository.dashboard.DashboardRepositoryImpl;
import ru.miracle.madmeditation.domain.repository.DashboardRepository;

public class GetQuotesUseCaseImpl implements GetQuotesUseCase{

    private final DashboardRepository dashboardRepository = new DashboardRepositoryImpl();

    @Override
    public Single<QuotesResponse> getQuotes() {
        return dashboardRepository.getQuotes();
    }
}
