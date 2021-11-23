package ru.miracle.madmeditation.data.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.domain.entities.PhotoDto;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    Single<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    Single<List<User>> getAllUsersByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE uid LIKE :uid")
    Single<User> getUser(int uid);

    @Query("SELECT * FROM user WHERE login LIKE :login AND " +
            "password LIKE :password LIMIT 1")
    Single<User> findByLoginPassword(String login, String password);

    @Insert
    Completable insertUsers(User... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);
}
