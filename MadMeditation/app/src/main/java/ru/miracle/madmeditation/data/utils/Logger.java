package ru.miracle.madmeditation.data.utils;

import android.util.Log;

public class Logger {
    static public void log(Object any, String message) {
        Log.d(any.toString(), message);
    }

    static public void error(Throwable exception) {
        exception.printStackTrace();
    }
}
