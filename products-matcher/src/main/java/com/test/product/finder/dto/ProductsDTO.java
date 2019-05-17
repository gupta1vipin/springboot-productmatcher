package com.test.product.finder.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductsDTO {

	/** The products. */
	List<ProductDTO> products = new ArrayList<ProductDTO>();

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
