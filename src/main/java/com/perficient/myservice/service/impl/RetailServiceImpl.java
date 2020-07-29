package com.perficient.myservice.service.impl;

import com.perficient.myservice.service.RetailService;
import com.perficient.myservice.types.ProductInfoRequest;
import com.perficient.myservice.types.ProductInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RetailServiceImpl implements RetailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${createProductUrl}")
    private String createProductsUrl;

    @Value("${getProductUrl}")
    private String getProductsUrl;

    @Override
    public ProductInfoResponse createRetailInfo(ProductInfoRequest request) {
        logger.info("URL for createProducts >>>{}", createProductsUrl);
        return restTemplate.postForObject(createProductsUrl,request, ProductInfoResponse.class);
    }

    @Override
    public ProductInfoResponse getRetailInfo(String productName) {
        logger.info("URL for getProducts >>>{}", getProductsUrl+productName);
        return restTemplate.getForObject(getProductsUrl+productName, ProductInfoResponse.class);
    }


}
