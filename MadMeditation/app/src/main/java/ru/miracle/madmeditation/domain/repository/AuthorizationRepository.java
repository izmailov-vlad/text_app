package ru.miracle.madmeditation.domain.repository;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;

public interface AuthorizationRepository {
    Single<User> logIn(String login, String password);
    Single<Boolean> logOut();
    Completable register(String login, String password);
    Single<List<User>> getAllUsers();
    Single<User> getUser();

}
