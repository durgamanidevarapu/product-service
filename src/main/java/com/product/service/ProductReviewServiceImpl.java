package com.product.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.product.domain.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="productReviewService")
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EurekaClient eurekaClient;

    private String SERVICE_NAME="REVIEW-SERVICE";

    @Override
    public ResponseEntity<Object> addReview(ReviewDto reviewDto,Long productId) {
        String uri = getServiceUrl()+"/products/"+productId+"/reviews";
        HttpEntity<ReviewDto> request = new HttpEntity<>(reviewDto);
        return restTemplate.exchange(uri, HttpMethod.POST, request, Object.class);
    }

    @Override
    public List<ReviewDto> getProductReviews(Long productId) {
        String uri = getServiceUrl()+productId+"/reviews";
        return restTemplate.exchange(uri,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<ReviewDto>>() {
                }).getBody();
    }


    private String getServiceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(SERVICE_NAME, false);
        return instance.getHomePageUrl();
    }
}
