package ru.miracle.madmeditation.domain.usecase.register;

import io.reactivex.Completable;

public interface RegisterUserUseCase {
    Completable register(String login, String password);
}
