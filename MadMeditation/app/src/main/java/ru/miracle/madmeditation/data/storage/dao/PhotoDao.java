package ru.miracle.madmeditation.data.storage.dao;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.miracle.madmeditation.data.models.photo.Photo;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photo")
    Single<List<Photo>> getAllPhotos();

    @Insert
    Completable insertPhoto(Photo photoDto);

    @Query("DELETE FROM photo WHERE uid = :photoUid")
    Completable deleteByPhotoUid(int photoUid);
}
