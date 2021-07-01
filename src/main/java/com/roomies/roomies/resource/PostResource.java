package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class PostResource extends AuditModel {
    private Long id;
    private String title;
    private String address;
    private String district;
    private String department;
    private float price;
    private int roomQuantity;
    private int bathQuantity;

    public Long getId() {
        return id;
    }

    public PostResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PostResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PostResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public PostResource setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public PostResource setDepartment(String department) {
        this.department = department;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public PostResource setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public PostResource setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
        return this;
    }

    public int getBathQuantity() {
        return bathQuantity;
    }

    public PostResource setBathQuantity(int bathQuantity) {
        this.bathQuantity = bathQuantity;
        return this;
    }
}
