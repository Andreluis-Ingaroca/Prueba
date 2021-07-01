package com.roomies.roomies.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="leaseholders")
public class Leaseholder extends Profile {

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    , mappedBy = "leaseholders")
    private List<Post> favouritePosts;

    public Leaseholder() {
    }

    public List<Post> getFavouritePosts() {
        return favouritePosts;
    }

    public boolean isTaggedWith(Post post){ return this.getFavouritePosts().contains(post);}

    public Leaseholder assignWith(Post post){
        if(!this.isTaggedWith(post))
            this.getFavouritePosts().add(post);
        return this;
    }

    public Leaseholder unAssignWith(Post post){
        if(this.isTaggedWith(post))
            this.getFavouritePosts().remove(post);
        return this;
    }

}
