package ru.miracle.madmeditation.domain.repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.CatResponse;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.domain.entities.PhotoDto;

public interface ProfileRepository {
    Single<CatResponse> getCat();
    Single<List<Photo>> getAllPhotos();
    Completable insertPhoto(Photo photo);
    Completable deletePhoto(int uid);
}
