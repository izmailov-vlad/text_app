package ru.miracle.madmeditation.data.repository.profile;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.CatResponse;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.data.network.CatGeneratorApi;
import ru.miracle.madmeditation.data.storage.dao.PhotoDao;
import ru.miracle.madmeditation.domain.entities.PhotoDto;
import ru.miracle.madmeditation.domain.repository.ProfileRepository;

public class ProfileRepositoryImpl implements ProfileRepository {
    private PhotoDao photoDao;
    private final CatGeneratorApi catGeneratorApi = CatGeneratorApi.getInstance();

    public ProfileRepositoryImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public Single<CatResponse> getCat() {
        return catGeneratorApi.getService().getCat();
    }

    @Override
    public Single<List<Photo>> getAllPhotos() {
        // TODO should use map to Dto model
        return photoDao.getAllPhotos();
    }

    @Override
    public Completable insertPhoto(Photo photoDto) {
        return photoDao.insertPhoto(photoDto);
    }

    @Override
    public Completable deletePhoto(int uid) {
        return photoDao.deleteByPhotoUid(uid);
    }
}
