package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class PlanResource extends AuditModel {
    private Long id;
    private float price;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public PlanResource setId(Long id) {
        this.id = id;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public PlanResource setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlanResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
