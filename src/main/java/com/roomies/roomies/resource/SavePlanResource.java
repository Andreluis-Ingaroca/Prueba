package com.roomies.roomies.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SavePlanResource {

    @NotNull
    private float price;

    @NotNull
    private String name;

    @NotNull
    @Lob
    private String description;

    public float getPrice() {
        return price;
    }

    public SavePlanResource setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public SavePlanResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SavePlanResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
