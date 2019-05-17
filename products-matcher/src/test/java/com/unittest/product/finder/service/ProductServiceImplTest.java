package com.unittest.product.finder.service;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.product.finder.dto.ProductDTO;
import com.test.product.finder.dto.ProductsDTO;
import com.test.product.finder.masterdata.MasterDataManagementUtil;
import com.test.product.finder.service.ProductService;
import com.test.product.finder.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

	ProductService productService = null;
	ProductsDTO productsDTO = null;
	ProductDTO product = null;
	int inputProductId = (int) Math.random();
	String inputProductName = null;
	String[] inputTagArray1 = new String[] { "veludo", "basics", "elastano" };
	int[] expectedTagVector1 = new int[] { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
	String[] masterTagArray = MasterDataManagementUtil.getProductTagsArray();

	@Before
	public void setup() {
		productService = new ProductServiceImpl();
		this.setupDefaultProductTestData(inputProductId, inputProductName, inputTagArray1);
	}

	@Test
	public void testUpdateVectorIfProductIsNull() {
		try {
			productService.updateTagVectorForProduct(null);
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	public void testUpdateVectorIfProductIsEmpty() {
		try {
			productService.updateTagVectorForProduct(new ProductDTO());
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	public void testUpdateVectorIfProductTagArrayIsEmpty() {
		try {
			product.setTags(new String[0]);
			productService.updateTagVectorForProduct(product);
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	public void testUpdateVectorForProduct() {
		Assert.assertNull("Initial vector is null", product.getTagVector());
		productService.updateTagVectorForProduct(product);

		Assert.assertNotNull("Result vector is not null", product.getTagVector());

		Assert.assertArrayEquals(expectedTagVector1, product.getTagVector());
	}

	@Test
	public void testUpdateTagVectorsForProductsIfEmptyInput() {

		Map<Integer, ProductDTO> result = null;
		try {
			result = productService.updateTagVectorsForProducts(null);
			Assert.assertNotNull(result);
			Assert.assertTrue(result.size() == 0);

			result = productService.updateTagVectorsForProducts(new ProductsDTO());
			Assert.assertNotNull(result);
			Assert.assertTrue(result.size() == 0);
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	public void testUpdateTagVectorsForProducts() {

		Map<Integer, ProductDTO> result = null;
		try {
			result = productService.updateTagVectorsForProducts(productsDTO);
			Assert.assertNotNull(result);
			Assert.assertTrue(result.size() == productsDTO.getProducts().size());

			Assert.assertArrayEquals(inputTagArray1, result.get(product.getId()).getTags());
			Assert.assertArrayEquals(expectedTagVector1, result.get(product.getId()).getTagVector());

		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	public void testfindSimilarProductsByVectorMatchWithOneProductInput() {

		try {
			Map<Integer, ProductDTO> inputProductWithVectors = productService.updateTagVectorsForProducts(productsDTO);
			List<ProductDTO> result = productService.findSimilarProductsByVectorMatch(inputProductId,
					inputProductWithVectors);

			Assert.assertNotNull(result);
			Assert.assertTrue(result.size() <= ProductServiceImpl.MAX_SIMILAR_PRODUCTS);

			for (ProductDTO resultProductDto : result) {

				if (inputProductWithVectors.values().stream()
						.filter((obj -> ((obj.getSimilarityIndex() > resultProductDto.getSimilarityIndex())))).findAny()
						.isPresent()) {
					fail("found a product with a greater similarity which is not returned in function");
				}
			}

		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	public void testfindSimilarProductsByVectorMatchWithMultipleProductInput() {

		try {

			this.createRandomProductInputData();

			Map<Integer, ProductDTO> inputProductWithVectors = productService.updateTagVectorsForProducts(productsDTO);

			List<ProductDTO> result = productService.findSimilarProductsByVectorMatch(inputProductId,
					inputProductWithVectors);

			Assert.assertNotNull(result);
			Assert.assertTrue(result.size() <= ProductServiceImpl.MAX_SIMILAR_PRODUCTS);

		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}

	private void createRandomProductInputData() {
		productsDTO = new ProductsDTO();
		for (int i = 0; i < 9; i++) {
			ProductDTO product = new ProductDTO();
			product.setId(i);
			product.setName("Name" + i);
			String[] tags = new String[3];
			for (int j = 0; j <= 2; j++) {
				tags[j] = MasterDataManagementUtil.getProductTagsArray()[getRandomNumberInRange(0, 19)];
			}
			product.setTags(tags);
			productsDTO.getProducts().add(product);
		}
	}

	private void setupDefaultProductTestData(int productId, String productName, String[] tagArray) {
		product = new ProductDTO();
		product.setId(productId);
		product.setName(productName);
		product.setTags(tagArray);

		productsDTO = new ProductsDTO();
		productsDTO.getProducts().add(product);
	}

	private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();

	}

}
