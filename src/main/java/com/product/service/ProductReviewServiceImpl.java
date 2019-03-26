package com.product.service;

import com.netflix.discovery.EurekaClient;
import com.product.ProductReviewClient;
import com.product.domain.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service(value="productReviewService")
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EurekaClient eurekaClient;

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
