package com.perficient.myservice.service;

import com.perficient.myservice.types.ProductInfoRequest;
import com.perficient.myservice.types.ProductInfoResponse;

public interface RetailService {

    ProductInfoResponse createRetailInfo(ProductInfoRequest request);

    ProductInfoResponse getRetailInfo(String productName);
}
