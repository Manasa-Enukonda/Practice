package com.mypractice.dailytask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypractice.dailytask.dao.ProductRepository;
import com.mypractice.dailytask.exception.DuplicateEntityException;
import com.mypractice.dailytask.service.ProductService;
import com.mypractice.dailytask.types.Product;
import com.mypractice.dailytask.types.ProductInfoRequest;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional
	public void save(ProductInfoRequest productRequest) throws DuplicateEntityException {
		try {
			final Product productFound = productRepository.findByName(productRequest.getName());
			if(null!=productFound) {
				throw new DuplicateEntityException("ProductName " + productRequest.getName() + " already exists.");
			}
			productRepository.save(mapRequestToEntity(productRequest));
		}catch(DuplicateEntityException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private Product mapRequestToEntity(ProductInfoRequest productRequest) {
		Product product= new Product();
		product.setName(productRequest.getName());
		product.setCost(productRequest.getCost());
		product.setInStock(productRequest.isInStock());
		return product;
	}

}
