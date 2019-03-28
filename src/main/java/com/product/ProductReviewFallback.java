package com.product;

import com.product.domain.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductReviewFallback implements  ProductReviewClient {
    @Override
    public List<ReviewDto> getProductReviews(Long productId) {
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity<Object> addProductReview(ReviewDto reviewDto, Long productId) {
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
}
