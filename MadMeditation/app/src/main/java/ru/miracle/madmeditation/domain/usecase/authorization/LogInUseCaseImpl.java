package ru.miracle.madmeditation.domain.usecase.authorization;


import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.domain.repository.AuthorizationRepository;

public class LogInUseCaseImpl implements LogInUseCase{
    private final AuthorizationRepository authorizationRepository;

    public LogInUseCaseImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Single<User> logIn(String login, String password) {
        return authorizationRepository.logIn(login, password);
    }
}
