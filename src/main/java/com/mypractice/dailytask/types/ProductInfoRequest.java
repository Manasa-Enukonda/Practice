package com.mypractice.dailytask.types;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductInfoRequest {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String name ;
	

	private Integer cost;
	

	private boolean inStock;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	

}
