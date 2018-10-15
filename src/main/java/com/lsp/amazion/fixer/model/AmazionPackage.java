package com.lsp.amazion.fixer.model;

import java.util.List;

public class AmazionPackage {

	private String id;
	private String name;
	private String description;
	private List<Product> products;
	private Integer price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
		calculatePrice(products);
	}

	private void calculatePrice(List<Product> products) {
		this.price = products.stream().mapToInt(p -> p.getUsdPrice()).sum();
	}

}
