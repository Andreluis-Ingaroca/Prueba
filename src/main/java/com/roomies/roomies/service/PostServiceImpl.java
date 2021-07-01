package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.Landlord;
import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.model.Post;
import com.roomies.roomies.domain.model.Review;
import com.roomies.roomies.domain.repository.LandlordRepository;
import com.roomies.roomies.domain.repository.LeaseholderRepository;
import com.roomies.roomies.domain.repository.PostRepository;
import com.roomies.roomies.domain.repository.ReviewRepository;
import com.roomies.roomies.domain.service.PostService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private LeaseholderRepository leaseholderRepository;

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
    }

    @Override
    public Post createPost(Long landlordId,Post post) {
        return landlordRepository.findById(landlordId).map(
                landlord ->{landlord.addPost(post);
                    return postRepository.save(post);
                }).orElseThrow(()->new ResourceNotFoundException(
                        "Landlord","Id",landlordId));
    }

    @Override
    public Post updatePost(Long postId, Post postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        post.setAddress(postRequest.getAddress())
                .setDepartment(postRequest.getDepartment())
                .setDistrict(postRequest.getDistrict())
                .setPrice(postRequest.getPrice())
                .setBathQuantity(postRequest.getBathQuantity())
                .setRoomQuantity(postRequest.getRoomQuantity());
        return postRepository.save(post);
    }

    @Override
    public ResponseEntity<?> deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));

        Pageable pageable = PageRequest.of(0,1000);
        Page<Review> reviewPage = reviewRepository.findByPostId(postId,pageable);
        Page<Leaseholder> leaseholderPage = getAllLeaseholdersByPostId(postId,pageable);

        if(reviewPage!=null)
            reviewPage.forEach(review -> {reviewRepository.delete(review);});

        if(leaseholderPage!=null)
            leaseholderPage.forEach(leaseholder -> {
                leaseholder.unAssignWith(post);
            });

        postRepository.delete(post);

        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Leaseholder> getAllLeaseholdersByPostId(Long postId, Pageable pageable) {
        return postRepository.findById(postId).map(
                post -> {List<Leaseholder> leaseholders = post.getLeaseholderFavouritePosts();
                int leaseholdersCount = leaseholders.size();
                return new PageImpl<>(leaseholders,pageable,leaseholdersCount);
                }).orElseThrow(()->new ResourceNotFoundException(
                        "Post","Id",postId));
    }


    @Override
    public Post getPostByTitle(String title) {

        return postRepository.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException("Post","Title",title));
    }
}
