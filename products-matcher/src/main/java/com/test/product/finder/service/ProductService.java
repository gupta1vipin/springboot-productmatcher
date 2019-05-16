package com.test.product.finder.service;

import java.util.List;
import java.util.Map;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.dto.ProductsDTO;

/**
 * The Interface ProductFinderService declares function definitions for Product
 * Tag Vector and related business requirements.
 */
public interface ProductService {

	/**
	 * Fetch matching tag vectors for products. This function creates a vector of
	 * tags of dimension 20, where each position of the vector corresponds to a
	 * characteristic and has a value equal to 1 if the product has the
	 * characteristic, otherwise the value is equal to zero
	 *
	 * @param prodListObj the prod list obj
	 * @return the products DTO
	 */
	ProductsDTO fetchMatchingTagVectorsForProducts(final ProductsDTO prodListObj);

	/**
	 * Update tag vector for provided product.
	 *
	 * @param product the product
	 */
	void updateTagVectorForProduct(ProductDTO product);

	/**
	 * Fetch matching tag vectors map for products. It creates a Map<Product Id,
	 * Product DTO>.
	 *
	 * @param prodListObj the prod list obj
	 * @return the map
	 */
	Map<Integer, ProductDTO> fetchMatchingTagVectorsMapForProductsMap(final ProductsDTO prodListObj);

	/**
	 * Find similar products by vector match.
	 *
	 * @param id          the id of product
	 * @param productsMap the products map
	 * @return the list
	 */
	List<ProductDTO> findSimilarProductsByVectorMatch(final Integer id, final Map<Integer, ProductDTO> productsMap);

}
