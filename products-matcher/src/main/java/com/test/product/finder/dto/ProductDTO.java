package com.test.product.finder.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class Product.
 */
@JsonInclude(Include.NON_EMPTY)
public class ProductDTO {

	/** The name : product name */
	@NotBlank
	private String name;

	/** The id : unique identifier of the product */
	@NotNull
	@Min(0)
	private int id;

	/** The tags : array of strings with product characteristics */
	@NotEmpty
	private String[] tags;

	private int[] tagVector;
	
	//@JsonIgnore
	private double similarityIndex;

	public double getSimilarityIndex() {
		return similarityIndex;
	}

	public void setSimilarityIndex(double similarityIndex) {
		this.similarityIndex = similarityIndex;
	}

	public int[] getTagVector() {
		return tagVector;
	}

	public void setTagVector(int[] tagVector) {
		this.tagVector = tagVector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

}
