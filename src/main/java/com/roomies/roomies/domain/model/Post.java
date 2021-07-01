package com.roomies.roomies.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name ="posts")
public class Post extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY,
    cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "favorite_posts",
    joinColumns = {@JoinColumn(name = "post_id")},
    inverseJoinColumns = {@JoinColumn(name = "leaseholder_id")})
    private List<Leaseholder> leaseholders;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Post setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Post setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Post setDepartment(String department) {
        this.department = department;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Post setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public Post setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
        return this;
    }

    public int getBathQuantity() {
        return bathQuantity;
    }

    public Post setBathQuantity(int bathQuantity) {
        this.bathQuantity = bathQuantity;
        return this;
    }

    public List<Leaseholder> getLeaseholderFavouritePosts() {
        return leaseholders;
    }

    public boolean isTaggedWith(Leaseholder leaseholder){return this.getLeaseholderFavouritePosts().contains(leaseholder);}

    public Post unAssignWith(Leaseholder leaseholder){
        if(this.isTaggedWith(leaseholder))
            this.getLeaseholderFavouritePosts().remove(leaseholder);
        return this;
    }

}
