package ru.miracle.madmeditation.domain.usecase.user;

import java.util.List;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.domain.repository.AuthorizationRepository;

public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase{
    private final AuthorizationRepository authorizationRepository;
    public GetAllUsersUseCaseImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Single<List<User>> getAllUsers() {
        return authorizationRepository.getAllUsers();
    }
}
