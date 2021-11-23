package ru.miracle.madmeditation.domain.usecase.photos;

import io.reactivex.Completable;
import ru.miracle.madmeditation.data.models.photo.Photo;

public interface InsertPhotoUseCase {
    Completable execute(Photo photo);
}
