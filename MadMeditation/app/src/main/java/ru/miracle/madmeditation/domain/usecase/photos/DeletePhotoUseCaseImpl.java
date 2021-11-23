package ru.miracle.madmeditation.domain.usecase.photos;

import io.reactivex.Completable;
import ru.miracle.madmeditation.domain.repository.ProfileRepository;

public class DeletePhotoUseCaseImpl implements DeletePhotoUseCase {
    private final ProfileRepository profileRepository;
    public DeletePhotoUseCaseImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Completable execute(int uid) {
        return profileRepository.deletePhoto(uid);
    }
}
