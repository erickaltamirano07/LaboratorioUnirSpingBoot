package com.laboratorio.products.service;

import java.util.List;

import com.laboratorio.products.model.pojo.Product;
import com.laboratorio.products.model.pojo.ProductDto;
import com.laboratorio.products.model.request.CreateProductRequest;

public interface ProductsService {
	
	List<Product> getProducts(String name, String category, Float price, String color,
							String peso, String tamanio, String empresa, String url, Boolean disponilble);
	
	Product getProduct(String productId);
	
	Boolean removeProduct(String productId);
	
	Product createProduct(CreateProductRequest request);

	Product updateProduct(String productId, String updateRequest);

	Product updateProduct(String productId, ProductDto updateRequest);

}
