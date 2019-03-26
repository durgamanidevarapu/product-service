package com.product.service;

import com.product.domain.ReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductReviewService {
    public ResponseEntity<Object> addReview(ReviewDto reviewDto);
    public List<ReviewDto> getProductReviews(long productId);

}
