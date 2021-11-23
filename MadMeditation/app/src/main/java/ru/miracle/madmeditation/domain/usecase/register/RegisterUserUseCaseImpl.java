package ru.miracle.madmeditation.domain.usecase.register;

import io.reactivex.Completable;
import ru.miracle.madmeditation.domain.repository.AuthorizationRepository;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    private final AuthorizationRepository authorizationRepository;

    public RegisterUserUseCaseImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Completable register(String login, String password) {
        return authorizationRepository.register(login, password);
    }
}
