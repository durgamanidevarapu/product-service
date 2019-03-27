package com.product;

import com.product.domain.ReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

public class ProductReviewFallback implements  ProductReviewClient {
    @Override
    public List<ReviewDto> getProductReviews(Long productId) {
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity<Object> addProductReview(ReviewDto reviewDto, Long productId) {
        return null;
    }
}
