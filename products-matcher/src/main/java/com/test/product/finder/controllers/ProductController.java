package com.test.product.finder.controllers;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.dto.ProductsDTO;
import com.test.product.finder.service.ProductService;

@RestController
public class ProductController {
	
	// TODO (either add logging here or implement as AOP )
	private static final Logger LOG = Logger.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	

	/**
	 * Fetch tag vectors for products.
	 *
	 * @param prodListObj the prod list obj
	 * @return the products DTO
	 */
	@RequestMapping(value = "/fetch-matching-tagvectors", method = { RequestMethod.POST })
	public ProductsDTO fetchTagVectorsForProducts(@RequestBody final ProductsDTO prodListObj) {

		return productService.fetchMatchingTagVectorsForProducts(prodListObj);
	}
	
	
	@RequestMapping(value = "/fetch-matching-tagvectors-map", method = { RequestMethod.POST })
	public Map<Integer, ProductDTO> fetchMatchingTagVectorsMapForProductsMap(@RequestBody final ProductsDTO prodListObj) {

		return productService.fetchMatchingTagVectorsMapForProductsMap(prodListObj);
	}
	
	
	/**
	 * Find similar products with SpringRequestBody. This API returns the selected
	 * id product from provided data.
	 *
	 * @param id          the id
	 * @param prodListObj the prod list obj
	 * @return the products DTO
	 */
	@RequestMapping(value = "/fetch-similar-products/{id}", method = { RequestMethod.POST })
	public List<ProductDTO> findSimilarProducts(@PathVariable final Integer id,
			@RequestBody final Map<Integer, ProductDTO> productsMap) {

		 return productService.findSimilarProductsByVectorMatch(id, productsMap);
	}

}
