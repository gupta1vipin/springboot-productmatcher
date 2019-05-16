package com.test.product.finder.controllers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.service.ProductTagService;

@RestController
public class ProductFinderController {
	
	// TODO (either add logging here or implement as AOP )
	private static final Logger LOG = Logger.getLogger(ProductFinderController.class);

	@Autowired
	ProductTagService productFinderService;

	/**
	 * Find similar products with SpringRequestBody. This API returns the selected id product from provided data.
	 *
	 * @param id the id
	 * @param prodListObj the prod list obj
	 * @return the products DTO
	 */
	@RequestMapping(value = "/fetch-similar-products/{id}", method = { RequestMethod.POST })
	public ProductDTO findSimilarProducts(@PathVariable final Integer id, @RequestBody final Map<Integer, ProductDTO> productsMap) {

		ProductDTO prod = null;
		if(null !=productsMap && productsMap.containsKey(id)) {
			prod = productsMap.get(id);
		}
		return prod;
	}

}
