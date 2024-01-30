package com.laboratorio.products.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.laboratorio.products.model.pojo.Product;
import com.laboratorio.products.model.pojo.ProductDto;
import com.laboratorio.products.data.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.laboratorio.products.model.request.CreateProductRequest;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Product> getProducts(String name, String category, Float price, String color,
									 String peso, String tamanio, String empresa, String url, Boolean disponilble) {

		if (StringUtils.hasLength(name) || StringUtils.hasLength(category) || price!=null|| StringUtils.hasLength(color)
				||StringUtils.hasLength(peso) ||StringUtils.hasLength(tamanio) ||StringUtils.hasLength(empresa)
				||StringUtils.hasLength(url) || disponilble != null) {
			return repository.search(name, category, price, color,peso, tamanio, empresa, url, disponilble);
		}

		List<Product> products = repository.getProducts();
		return products.isEmpty() ? null : products;
	}

	@Override
	public Product getProduct(String productId) {
		return repository.getById(Long.valueOf(productId));
	}

	@Override
	public Boolean removeProduct(String productId) {

		Product product = repository.getById(Long.valueOf(productId));

		if (product != null) {
			repository.delete(product);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Product createProduct(CreateProductRequest request) {

		//Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getCategory().trim())
				&& request.getPrice()!=null && StringUtils.hasLength(request.getColor().trim())
				&& StringUtils.hasLength(request.getPeso().trim())
				&& StringUtils.hasLength(request.getTamanio().trim())
				&& StringUtils.hasLength(request.getEmpresa().trim())
				&& StringUtils.hasLength(request.getUrl().trim())
				&& request.getDisponilble()!=null) {

			Product product = Product.builder().name(request.getName())
					.category(request.getCategory()).price(request.getPrice())
					.color(request.getColor()).peso(request.getPeso())
					.tamanio(request.getTamanio()).empresa(request.getEmpresa())
					.url(request.getUrl()).disponilble(request.getDisponilble()).build();

			return repository.save(product);
		} else {
			return null;
		}
	}

	@Override
	public Product updateProduct(String productId, String request) {

		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Product product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
				Product patched = objectMapper.treeToValue(target, Product.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating product {}", productId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Product updateProduct(String productId, ProductDto updateRequest) {
		Product product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			product.update(updateRequest);
			repository.save(product);
			return product;
		} else {
			return null;
		}
	}

}
