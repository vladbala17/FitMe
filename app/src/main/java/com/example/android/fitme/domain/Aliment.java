package com.example.android.fitme.domain;

/**
 * Created by vlad on 22.08.2017.
 */

public class Aliment {
    private String name;
    private String protein;
    private String carbs;
    private String fats;

    public Aliment(String name, String protein, String carbs, String fats) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public Aliment() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }
}
