package ru.miracle.madmeditation.data.models;

public class CatResponse {
    String file;

    public CatResponse(String file) {
        this.file = file;
    }

    public String getCatImage() {
        return file;
    }
}
