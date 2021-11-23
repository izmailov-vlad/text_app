package ru.miracle.madmeditation.data.storage.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import kotlin.jvm.Volatile;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.storage.dao.PhotoDao;
import ru.miracle.madmeditation.data.storage.dao.UserDao;

@Database(entities = {User.class, Photo.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PhotoDao photoDao();

    @Volatile
    static AppDatabase instance;

    public static AppDatabase getAppDatabase(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            )
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }

    static final String DATABASE_NAME = "APP_DATABASE";
}
