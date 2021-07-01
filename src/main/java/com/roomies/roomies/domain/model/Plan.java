package com.roomies.roomies.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name ="plans")
public class Plan extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private float price;

    @NotNull
    private String name;

    @NotNull
    @Lob
    private String description;

    public Plan() {
    }

    public Long getId() {
        return id;
    }

    public Plan setId(Long id) {
        this.id = id;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Plan setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public Plan setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Plan setDescription(String description) {
        this.description = description;
        return this;
    }
}
