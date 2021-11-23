package ru.miracle.madmeditation.domain.entities;

public class QuotesDto {
    int id;
    String title;
    String image;
    String description;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public QuotesDto(int id, String title, String image, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }
}
