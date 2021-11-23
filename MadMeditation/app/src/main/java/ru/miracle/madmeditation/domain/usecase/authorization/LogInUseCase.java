package ru.miracle.madmeditation.domain.usecase.authorization;


import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;

public interface LogInUseCase {
    Single<User> logIn(String login, String password);
}
