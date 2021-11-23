package ru.miracle.madmeditation.domain.entities;

public class FeelingsDto {
    Integer id;
    Integer position;
    String title;
    String image;
    String description;

    public FeelingsDto(Integer id, String title, String image, String description, Integer position) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.position = position;
        this.description = description;
    }

    public Integer getId() {
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

    public Integer getPosition() {
        return position;
    }
}
