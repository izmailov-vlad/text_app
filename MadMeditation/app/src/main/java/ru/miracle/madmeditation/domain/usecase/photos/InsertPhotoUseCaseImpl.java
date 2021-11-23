package ru.miracle.madmeditation.domain.usecase.photos;

import io.reactivex.Completable;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.domain.repository.ProfileRepository;

public class InsertPhotoUseCaseImpl implements InsertPhotoUseCase {
    private final ProfileRepository profileRepository;

    public InsertPhotoUseCaseImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Completable execute(Photo photo) {
        return profileRepository.insertPhoto(photo);
    }
}
