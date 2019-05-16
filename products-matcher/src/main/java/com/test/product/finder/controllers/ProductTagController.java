package com.test.product.finder.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.dto.ProductsDTO;
import com.test.product.finder.service.ProductTagService;

@RestController
public class ProductTagController {

	@Autowired
	ProductTagService productFinderService;

	/**
	 * Fetch tag vectors for products.
	 *
	 * @param prodListObj the prod list obj
	 * @return the products DTO
	 */
	@RequestMapping(value = "/fetch-matching-tagvectors", method = { RequestMethod.POST })
	public ProductsDTO fetchTagVectorsForProducts(@RequestBody final ProductsDTO prodListObj) {

		return productFinderService.fetchMatchingTagVectorsForProducts(prodListObj);
	}
	
	
	@RequestMapping(value = "/fetch-matching-tagvectors-map", method = { RequestMethod.POST })
	public Map<Integer, ProductDTO> fetchMatchingTagVectorsMapForProductsMap(@RequestBody final ProductsDTO prodListObj) {

		return productFinderService.fetchMatchingTagVectorsMapForProductsMap(prodListObj);
	}

}
