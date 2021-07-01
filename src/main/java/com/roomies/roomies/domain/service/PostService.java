package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostService {
    Page<Post> getAllPosts(Pageable pageable);
    Post getPostById(Long postId);
    Post createPost(Long landlordId,Post post);
    Post updatePost(Long postId, Post postRequest);
    ResponseEntity<?> deletePost(Long postId);
    Page<Leaseholder> getAllLeaseholdersByPostId(Long postId, Pageable pageable);

    Post getPostByTitle(String title);
}
