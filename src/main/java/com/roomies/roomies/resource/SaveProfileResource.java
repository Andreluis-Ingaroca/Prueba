package com.roomies.roomies.resource;

import com.sun.istack.Nullable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveProfileResource {
    @NotNull
    @Size(max = 100)
    @NaturalId
    protected String name;

    @NotNull
    @Size(max = 100)
    @NaturalId
    protected String lastName;

    @NotNull
    protected Long cellphone;


    //protected String idCard;

    @Lob
    protected String description;


    protected Date birthday;

    @NotNull
    protected String department;

    @NotNull
    protected String province;

    @NotNull
    protected String district;

    @NotNull
    protected String address;

    public String getName() {
        return name;
    }

    public SaveProfileResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getCellphone() {
        return cellphone;
    }

    public SaveProfileResource setCellphone(Long cellphone) {
        this.cellphone = cellphone;
        return this;
    }
/*
    public String getIdCard() {
        return idCard;
    }

    public SaveProfileResource setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }*/

    public String getDescription() {
        return description;
    }

    public SaveProfileResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public SaveProfileResource setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public SaveProfileResource setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public SaveProfileResource setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public SaveProfileResource setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SaveProfileResource setAddress(String address) {
        this.address = address;
        return this;
    }
}
