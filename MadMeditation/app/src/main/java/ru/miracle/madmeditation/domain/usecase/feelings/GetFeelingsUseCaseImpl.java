package ru.miracle.madmeditation.domain.usecase.feelings;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.repository.dashboard.DashboardRepositoryImpl;
import ru.miracle.madmeditation.domain.repository.DashboardRepository;


public class GetFeelingsUseCaseImpl implements GetFeelingsUseCase {

    private final DashboardRepository dashboardRepository = new DashboardRepositoryImpl();

    @Override
    public Single<FeelingsResponse> getFeelings() {
        return dashboardRepository.getFeelings();
    }
}
