package com.example.catcrazeapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Cat {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String description;
    private String temperament;
    private String origin;
    private String life_span;
    private String wikipedia_url;
    private int dog_friendly;
    private String url;


    public Cat(@NotNull String id, String name, String description,
               String temperament, String origin, String life_span,
               String wikipedia_url, int dog_friendly, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.temperament = temperament;
        this.origin = origin;
        this.life_span = life_span;
        this.wikipedia_url = wikipedia_url;
        this.dog_friendly = dog_friendly;
        this.url = url;
    }

    @NotNull
    public String getId() {
        return id;
    }
    @NotNull
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }

    public void setDog_friendly(int dog_friendly) {
        this.dog_friendly = dog_friendly;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
