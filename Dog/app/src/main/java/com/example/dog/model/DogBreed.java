package com.example.dog.model;

import com.google.gson.annotations.SerializedName;

public class DogBreed {
    @SerializedName("id")
    public final int id;
    @SerializedName("name")
    public final String name;
    @SerializedName("origin")
    public final String origin;
    @SerializedName("life_span")
    public final String lifeSpan;
    @SerializedName("url")
    public final String url;

    public DogBreed(int id, String name, String origin, String lifeSpan, String url) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.url = url;
    }
}
