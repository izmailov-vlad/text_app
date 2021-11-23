package ru.miracle.madmeditation.domain.usecase.user;

import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;

public interface GetUserInfoUseCase {
    Single<User> execute();
}
