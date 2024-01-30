package com.laboratorio.products.data;

import java.util.List;

import com.laboratorio.products.model.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


	List<Product> findByName(String name);
	List<Product> findByCategory(String category);
	List<Product> findByPrice(Float price);
	List<Product> findByColor(String color);
	List<Product> findByPeso(String peso);
	List<Product> findByTamanio(String tamanio);
	List<Product> findByEmpresa(String empresa);
	List<Product> findByUrl(String url);
	List<Product> findByDisponilble(Boolean disponible);
	List<Product> findByNameAndCategory(String name, String category);


}
