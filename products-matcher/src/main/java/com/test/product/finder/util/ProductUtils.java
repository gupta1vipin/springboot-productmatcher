package com.test.product.finder.util;

import org.apache.commons.math3.util.Precision;

import com.test.product.finder.dto.ProductDTO;

/**
 * The Class ProductUtils contains static helper methods for product type of objects.
 */
public class ProductUtils {
	
	/**
	 * Calculate distance between tag vectors.
	 *
	 * @param vector1 the vector 1
	 * @param vector2 the vector 2
	 * @return the double
	 */
	public static double calculateDistanceBetweenTagVectors(int[] vector1, int[] vector2) {
		double distance=0;
		if(null != vector1 && null != vector2 && vector1.length==vector2.length) {
			
			for(int i=0; i<vector1.length; i++) {
				distance = distance + Math.pow((vector1[i]-vector2[i]),2);  // (v1[n] - v2[n]) * (v1[n] - v2[n])
			}
			distance=Math.sqrt(distance);
		}
		return distance;
	}
	
	
	/**
	 * Calculate similarity between two products.
	 *
	 * @param product1 the product 1
	 * @param product2 the product 2
	 * @return the double
	 */
	public static double calculateSimilarityBetweenTwoProducts(ProductDTO product1, ProductDTO product2) {
		double similarity=0;
		if(null!=product1 && null!=product2) {
			double distance = calculateDistanceBetweenTagVectors(product1.getTagVector(), product2.getTagVector());
			similarity=1/(1+distance);
		}
		return Precision.round(similarity, 3);
	}

}
