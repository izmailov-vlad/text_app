package ru.miracle.madmeditation.domain.usecase.cat;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.CatResponse;
import ru.miracle.madmeditation.domain.repository.ProfileRepository;

public class GetCatUseCaseImpl implements GetCatUseCase{
    ProfileRepository profileRepository;
    public GetCatUseCaseImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Single<CatResponse> execute() {
        return profileRepository.getCat();
    }
}
