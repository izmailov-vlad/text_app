package ru.miracle.madmeditation.domain.entities;

import android.net.Uri;
import android.widget.ImageView;

public class PhotoDto {
    String time;
    Uri image;

    public PhotoDto(String time, Uri image) {
        this.time = time;
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public Uri getImage() {
        return image;
    }
}
