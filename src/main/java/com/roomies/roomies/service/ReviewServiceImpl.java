package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.Review;
import com.roomies.roomies.domain.repository.PostRepository;
import com.roomies.roomies.domain.repository.ReviewRepository;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.service.ReviewService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Review> getAllReviewsByPostId(Long postId,Pageable pageable) {
        return reviewRepository.findByPostId(postId,pageable);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResourceNotFoundException("Review","Id",reviewId));
    }

    @Override
    public Review createReview(Long postId,Long profileId,Review review) {
        return postRepository.findById(postId).map(
                post->{review.setPost(post);
                profileRepository.findById(profileId).map(
                        user -> {review.setProfile(user);
                            return reviewRepository.save(review);})
                        .orElseThrow(()->new ResourceNotFoundException("Profile","Id",profileId));
                    return reviewRepository.save(review);}
        ).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
    }

    @Override
    public Review updateReview(Long reviewId, Review reviewRequest) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResourceNotFoundException("Review","Id",reviewId));
        review.setContent(reviewRequest.getContent())
                .setStarQuantity(reviewRequest.getStarQuantity());
        return reviewRepository.save(review);
    }

    @Override
    public ResponseEntity<?> deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResourceNotFoundException("Review","Id",reviewId));
        reviewRepository.delete(review);
        return ResponseEntity.ok().build();
    }
}
