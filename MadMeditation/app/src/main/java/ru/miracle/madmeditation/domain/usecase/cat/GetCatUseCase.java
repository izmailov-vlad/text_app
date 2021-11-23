package ru.miracle.madmeditation.domain.usecase.cat;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.CatResponse;

public interface GetCatUseCase {
    Single<CatResponse> execute();
}
