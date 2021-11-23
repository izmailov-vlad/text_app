package ru.miracle.madmeditation.presentation.view.photo.gesture;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import ru.miracle.madmeditation.data.utils.Logger;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    boolean isImageScaled = false;

    public GestureListener(ImageView imageView) {
        this.imageView = imageView;
    }

    ImageView imageView;
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();


        if (!isImageScaled) imageView.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
            if (isImageScaled) imageView.animate().scaleX(1f).scaleY(1f).setDuration(500);
            isImageScaled = !isImageScaled;
        Logger.log("GestureListe" +
                "ner","Double Tap");

        return true;
    }

}
