package com.test.product.finder.masterdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Interface ProductTagMaster. This interface provides helper functions to
 * get various master data related to products.
 */
public interface MasterDataManagementUtil {

	/**
	 * Gets the product tags array.
	 *
	 * @return the product tags array
	 */
	static String[] getProductTagsArray() {

		List<String> productTagsList = new ArrayList<>();
		productTagsList.add("neutro");
		productTagsList.add("veludo");
		productTagsList.add("couro");
		productTagsList.add("basics");
		productTagsList.add("festa");
		productTagsList.add("workwear");
		productTagsList.add("inverno");
		productTagsList.add("boho");
		productTagsList.add("estampas");
		productTagsList.add("balada");
		productTagsList.add("colorido");
		productTagsList.add("casual");
		productTagsList.add("liso");
		productTagsList.add("moderno");
		productTagsList.add("passeio");
		productTagsList.add("metal");
		productTagsList.add("viagem");
		productTagsList.add("delicado");
		productTagsList.add("descolado");
		productTagsList.add("elastano");

		return productTagsList.toArray(new String[productTagsList.size()]);
	}

}
