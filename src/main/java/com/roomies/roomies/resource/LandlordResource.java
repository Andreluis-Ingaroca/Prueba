package com.roomies.roomies.resource;

import java.util.List;

public class LandlordResource extends ProfileResource {
    private List<PostResource> posts;

    public List<PostResource> getPosts() {
        return posts;
    }
}
