package com.roomies.roomies.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="profiles")
public abstract class Profile extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Size(max = 100)
    protected String name;

    @NotNull
    @Size(max = 100)
    protected String lastName;

    @NotNull
    protected Long cellphone;


    //protected String idCard;

    @NotNull
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

    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST,CascadeType.MERGE},
    mappedBy = "profiles")
    private List<PaymentMethod> profilePaymentMethods;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private Plan plan;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = true)
    @JsonIgnore
    private User user;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Profile setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Profile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getCellphone() {
        return cellphone;
    }

    public Profile setCellphone(Long cellphone) {
        this.cellphone = cellphone;
        return this;
    }
/*
    public String getIdCard() {
        return idCard;
    }

    public Profile setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }*/

    public String getDescription() {
        return description;
    }

    public Profile setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Profile setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Profile setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public Profile setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Profile setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Profile setAddress(String address) {
        this.address = address;
        return this;
    }
    public Plan getPlan() {
        return plan;
    }

    public Profile setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }

    public boolean isAssignedWithPm(PaymentMethod paymentMethod){
        return this.getProfilePaymentMethods().contains(paymentMethod);
    }

    public List<PaymentMethod> getProfilePaymentMethods() {
        return profilePaymentMethods;
    }

    public Profile assignWithPm(PaymentMethod paymentMethod){
        if(!this.isAssignedWithPm(paymentMethod))
            this.getProfilePaymentMethods().add(paymentMethod);
        return this;
    }

    public Profile unAssignWithPm(PaymentMethod paymentMethod){
        if(this.isAssignedWithPm(paymentMethod))
            this.getProfilePaymentMethods().remove(paymentMethod);
        return this;
    }

    public User getUser() {
        return user;
    }

    public Profile setUser(User user) {
        this.user = user;
        return this;
    }
}
