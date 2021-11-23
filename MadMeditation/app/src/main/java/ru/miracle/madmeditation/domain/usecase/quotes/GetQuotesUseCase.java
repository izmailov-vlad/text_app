package ru.miracle.madmeditation.domain.usecase.quotes;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.QuotesResponse;

public interface GetQuotesUseCase {
    Single<QuotesResponse> getQuotes();
}
