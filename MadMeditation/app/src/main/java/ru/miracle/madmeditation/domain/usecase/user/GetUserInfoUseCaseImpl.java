package ru.miracle.madmeditation.domain.usecase.user;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.domain.repository.AuthorizationRepository;

public class GetUserInfoUseCaseImpl implements GetUserInfoUseCase{
    private AuthorizationRepository authorizationRepository;
    public GetUserInfoUseCaseImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Single<User> execute() {
        return authorizationRepository.getUser();
    }
}
