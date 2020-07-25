package com.mypractice.dailytask.dao;

import org.springframework.data.repository.CrudRepository;

import com.mypractice.dailytask.types.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {
   Product findByName(String name);
}