package ru.miracle.madmeditation.domain.usecase.photos;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.domain.entities.PhotoDto;

public interface GetAllPhotosUseCase {
    Single<List<Photo>> execute();
}
