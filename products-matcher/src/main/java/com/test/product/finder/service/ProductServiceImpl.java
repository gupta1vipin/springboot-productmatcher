package com.test.product.finder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.dto.ProductsDTO;
import com.test.product.finder.masterdata.MasterDataManagementUtil;
import com.test.product.finder.util.ProductUtils;

@Component
public class ProductServiceImpl implements ProductService {

	public static final int MAX_SIMILAR_PRODUCTS = 3;

	// TODO (either add logging here or implement as AOP )
	private static final Logger LOG = LogManager.getLogger();

	String[] masterTagArray = MasterDataManagementUtil.getProductTagsArray();

	@Override
	@Deprecated
	public ProductsDTO fetchMatchingTagVectorsForProducts(final ProductsDTO prodListObj) {
		if (null != prodListObj && !CollectionUtils.isEmpty(prodListObj.getProducts())) {
			prodListObj.getProducts().parallelStream().forEach(product -> this.updateTagVectorForProduct(product));
		}
		return prodListObj;
	}

	@Override
	public Map<Integer, ProductDTO> updateTagVectorsForProducts(ProductsDTO productsDto) {

		Map<Integer, ProductDTO> productsMap = new HashMap<Integer, ProductDTO>();
		if (null != productsDto && !CollectionUtils.isEmpty(productsDto.getProducts())) {
			productsMap = productsDto.getProducts().stream()
					.collect(Collectors.toMap(ProductDTO::getId, Function.identity()));
			productsMap.values().forEach(product -> this.updateTagVectorForProduct(product));
		}
		return productsMap;
	}

	@Override
	public void updateTagVectorForProduct(ProductDTO product) {
		if (null != product && null != product.getTags()) {
			int[] tagVectorArray = new int[masterTagArray.length];
			for (int i = 0; i < masterTagArray.length; i++) {
				tagVectorArray[i] = Arrays.asList(product.getTags()).contains(masterTagArray[i]) ? 1 : 0;
			}
			product.setTagVector(tagVectorArray);
		}
	}

	@Override
	public List<ProductDTO> findSimilarProductsByVectorMatch(final Integer id,
			final Map<Integer, ProductDTO> productsMap) {
		List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
		if (null != productsMap && productsMap.containsKey(id)) {
			final ProductDTO referenceProduct = productsMap.get(id); // find the product for id provided as path
																		// variable
			productsMap.remove(id); // remove the product for the id so that it doesn't compare with itself
			productsMap.values().forEach(product -> product
					.setSimilarityIndex(ProductUtils.calculateSimilarityBetweenTwoProducts(referenceProduct, product))); // iterate
																															// to
																															// calculate
																															// the
																															// similarityIndex

			// sort items by similarity index in reversed order (highest first)
			Comparator<ProductDTO> comparator = Comparator.comparing(ProductDTO::getSimilarityIndex).reversed();
			listProduct = productsMap.values().stream().sorted(comparator).limit(MAX_SIMILAR_PRODUCTS)
					.collect(Collectors.toList());
		}
		return listProduct;
	}

}
