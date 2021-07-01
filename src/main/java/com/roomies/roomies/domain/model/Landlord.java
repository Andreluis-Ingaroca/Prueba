package com.roomies.roomies.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="landlords")
public class Landlord extends Profile {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id",nullable = false)
    private List<Post> posts;

    public Landlord() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Landlord addPost(Post post) {
        this.posts.add(post);
        return this;
    }
}
