package com.mypractice.dailytask.service;

import com.mypractice.dailytask.exception.DuplicateEntityException;
import com.mypractice.dailytask.types.ProductInfoRequest;

public interface ProductService {
	void save(ProductInfoRequest product) throws DuplicateEntityException;

}
