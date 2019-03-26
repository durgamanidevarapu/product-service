package com.product.service;

import com.product.ProductReviewClient;
import com.product.domain.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="productReviewService")
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    ProductReviewClient productReviewClient;


    @Override
    public ResponseEntity<Object> addReview(ReviewDto reviewDto,Long productId) {
        return productReviewClient.addProductReview(reviewDto,productId);
    }

    @Override
    public List<ReviewDto> getProductReviews(Long productId) {
        return productReviewClient.getProductReviews(productId);
    }
}
