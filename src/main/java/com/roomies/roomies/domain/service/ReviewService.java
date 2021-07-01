package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    Page<Review> getAllReviewsByPostId(Long postId,Pageable pageable);
    Review getReviewById(Long reviewId);
    Review createReview(Long postId,Long profileId,Review review);
    Review updateReview(Long reviewId,Review reviewRequest);
    ResponseEntity<?> deleteReview(Long reviewId);
}
