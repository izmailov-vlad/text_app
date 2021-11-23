package ru.miracle.madmeditation.domain.usecase.photos;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.CatResponse;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.domain.entities.PhotoDto;
import ru.miracle.madmeditation.domain.repository.ProfileRepository;
import ru.miracle.madmeditation.domain.usecase.cat.GetCatUseCase;

public class GetAllPhotosUseCaseImpl implements GetAllPhotosUseCase {
    private final ProfileRepository profileRepository;

    public GetAllPhotosUseCaseImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Single<List<Photo>> execute() {
        return profileRepository.getAllPhotos();
    }
}
