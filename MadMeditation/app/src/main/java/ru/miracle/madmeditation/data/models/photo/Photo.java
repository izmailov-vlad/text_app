package ru.miracle.madmeditation.data.models.photo;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Photo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "time")
    private final String time;

    @ColumnInfo(name = "image")
    private final String image;

    public Photo(String time, String image) {
        this.time = time;
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }
}
