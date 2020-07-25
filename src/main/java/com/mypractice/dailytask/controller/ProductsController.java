package com.mypractice.dailytask.controller;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mypractice.dailytask.exception.DuplicateEntityException;
import com.mypractice.dailytask.service.ProductService;
import com.mypractice.dailytask.types.ProductInfoRequest;

@RestController
@RequestMapping(value="/product",headers = "Accept=application/json" )
public class ProductsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired 
	private ProductService productService;
	
	@PostMapping(value="/save")
	public ResponseEntity<?> createProduct(final @RequestBody @Valid ProductInfoRequest request){
		LOGGER.debug("Adding user with username {}.", request.getName());
		try {
			productService.save(request);
			LOGGER.info("Added user with username {}.", request.getName());
		} catch (DuplicateEntityException e) {
			LOGGER.warn(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{save}")
				.buildAndExpand(request.getName()).toUri()).build();
		
	}

}
