package ru.miracle.madmeditation.data.repository.authorization;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.storage.dao.UserDao;
import ru.miracle.madmeditation.domain.repository.AuthorizationRepository;

public class AuthorizationRepositoryImpl implements AuthorizationRepository {
    private final UserDao userDao;
    public AuthorizationRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Single<User> logIn(String login, String password) {
        return userDao.findByLoginPassword(login, password);
    }

    @Override
    public Single<Boolean> logOut() {
        return Single.just(true);
    }

    @Override
    public Completable register(String login, String password) {
        return userDao.insertUser(new User(1, login, password));
    }

    @Override
    public Single<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Single<User> getUser() {
        return userDao.getUser(1);
    }
}
