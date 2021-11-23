package ru.miracle.madmeditation.domain.usecase.photos;

import io.reactivex.Completable;

public interface DeletePhotoUseCase {
    Completable execute(int uid);
}
