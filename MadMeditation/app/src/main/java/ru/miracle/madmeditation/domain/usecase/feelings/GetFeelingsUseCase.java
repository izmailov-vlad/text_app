package ru.miracle.madmeditation.domain.usecase.feelings;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.FeelingsResponse;

public interface GetFeelingsUseCase {
    Single<FeelingsResponse> getFeelings();
}
