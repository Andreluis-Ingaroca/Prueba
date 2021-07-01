package com.roomies.roomies.resource;

import com.roomies.roomies.domain.model.AuditModel;

import java.util.Date;
import java.util.List;

public class ProfileResource extends AuditModel {
    protected Long id;
    protected String name;
    protected String lastName;
    protected Long cellphone;
    //protected String idCard;
    protected String description;
    protected Date birthday;
    protected String department;
    protected String province;
    protected String district;
    protected String address;
    protected List<PaymentMethodResource> profilePaymentMethods;
    protected PlanResource plan;

    public Long getId() {
        return id;
    }

    public ProfileResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfileResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getCellphone() {
        return cellphone;
    }

    public ProfileResource setCellphone(Long cellphone) {
        this.cellphone = cellphone;
        return this;
    }
/*
    public String getIdCard() {
        return idCard;
    }

    public ProfileResource setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }*/

    public String getDescription() {
        return description;
    }

    public ProfileResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public ProfileResource setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public ProfileResource setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public ProfileResource setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public ProfileResource setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ProfileResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<PaymentMethodResource> getProfilePaymentMethods() {
        return profilePaymentMethods;
    }

    public ProfileResource setProfilePaymentMethods(List<PaymentMethodResource> profilePaymentMethods) {
        this.profilePaymentMethods = profilePaymentMethods;
        return this;
    }

    public PlanResource getPlan() {
        return plan;
    }

    public ProfileResource setPlan(PlanResource plan) {
        this.plan = plan;
        return this;
    }
}
