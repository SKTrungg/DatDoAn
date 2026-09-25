package com.example.datdoan;

import java.io.Serializable;

public class Food implements Serializable {
    private final int id;
    private final String name;
    private final String description;
    private final long price;
    private final int imageResId;
    private final String ingredients;
    private final String category;

    public Food(int id, String name, String description, long price, int imageResId, String ingredients, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.ingredients = ingredients;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getCategory() {
        return category;
    }
}