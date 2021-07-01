package com.roomies.roomies.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SavePostResource {

    @NotNull
    private String title;

    @NotNull
    @Lob
    private String address;

    @NotNull
    private String district;

    @NotNull
    private String department;

    @NotNull
    private float price;

    @NotNull
    private int roomQuantity;

    @NotNull
    private int bathQuantity;

    public String getTitle() {
        return title;
    }

    public SavePostResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SavePostResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public SavePostResource setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public SavePostResource setDepartment(String department) {
        this.department = department;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public SavePostResource setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public SavePostResource setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
        return this;
    }

    public int getBathQuantity() {
        return bathQuantity;
    }

    public SavePostResource setBathQuantity(int bathQuantity) {
        this.bathQuantity = bathQuantity;
        return this;
    }
}
