package com.test.product.finder.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.dto.ProductsDTO;
import com.test.product.finder.masterdata.MasterDataManagementUtil;

@Component
public class ProductTagServiceImpl implements ProductTagService {
	
	// TODO (either add logging here or implement as AOP )
	private static final Logger LOG = Logger.getLogger(ProductTagServiceImpl.class);

	String[] masterTagArray = MasterDataManagementUtil.getProductTagsArray();

	@Override
	public ProductsDTO fetchMatchingTagVectorsForProducts(final ProductsDTO prodListObj) {
		if (null != prodListObj && !CollectionUtils.isEmpty(prodListObj.getProducts())) {
			prodListObj.getProducts().parallelStream().forEach(product -> this.updateTagVectorForProduct(product));
		}
		return prodListObj;
	}

	
	@Override
	public Map<Integer, ProductDTO> fetchMatchingTagVectorsMapForProductsMap(ProductsDTO prodListObj) {
		
		Map<Integer, ProductDTO> productsMap = new HashMap<Integer, ProductDTO>();
		productsMap = prodListObj.getProducts().stream().collect( Collectors.toMap(ProductDTO::getId,
                Function.identity()) );
		productsMap.values().forEach(product -> this.updateTagVectorForProduct(product));
		return productsMap;
	}
	

	@Override
	public void updateTagVectorForProduct(ProductDTO product) {

		int[] tagVectorArray = new int[masterTagArray.length];
		for (int i = 0; i < masterTagArray.length; i++) {
			tagVectorArray[i] = Arrays.asList(product.getTags()).contains(masterTagArray[i]) ? 1 : 0;
		}
		product.setTagVector(tagVectorArray);
	}

}
