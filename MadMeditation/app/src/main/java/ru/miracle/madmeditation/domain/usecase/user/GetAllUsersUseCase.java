package ru.miracle.madmeditation.domain.usecase.user;

import java.util.List;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;

public interface GetAllUsersUseCase {
    Single<List<User>> getAllUsers();
}
