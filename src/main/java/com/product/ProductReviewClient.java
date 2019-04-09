package com.product;


import com.product.domain.ReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="NETFLIX-ZUUL-API-GATEWAY", fallback = ProductReviewFallback.class)
public interface ProductReviewClient {

    @GetMapping(value="/api/product-review/{productId}/reviews")
    List<ReviewDto> getProductReviews(@PathVariable(value="productId") Long productId);

    @PostMapping(value="/api/product-review/products/{productId}/reviews")
    ResponseEntity<Object> addProductReview(@RequestBody ReviewDto reviewDto, @PathVariable(value="productId") Long productId);

}
