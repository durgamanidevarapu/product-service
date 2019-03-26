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
public class ReviewServiceImpl implements ProductReviewService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EurekaClient eurekaClient;

    private String SERVICE_NAME="REVIEW-SERVICE";

    @Override
    public ResponseEntity<Object> addReview(ReviewDto reviewDto) {
        String uri = getServiceUrl()+"/products/{productId}/reviews";
        Map<String, String> params = new HashMap<>();
        params.put("productId", "1");
        HttpEntity<ReviewDto> request = new HttpEntity<>(reviewDto);
        return restTemplate.exchange(uri, HttpMethod.POST, request, Object.class, params);
    }

    @Override
    public List<ReviewDto> getProductReviews(long productId) {
        String uri = getServiceUrl()+"/{productId}/reviews";
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        return restTemplate.exchange(uri,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<ReviewDto>>() {
                }).getBody();
    }


    private String getServiceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(SERVICE_NAME, false);
        return instance.getHomePageUrl();
    }
}
