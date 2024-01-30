package com.laboratorio.products.data;

import com.laboratorio.products.model.pojo.Product;
import com.laboratorio.products.data.utils.SearchCriteria;
import com.laboratorio.products.data.utils.SearchOperation;
import com.laboratorio.products.data.utils.SearchStatement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public List<Product> search(String name, String category, Float price, String color,
                                String peso, String tamanio, String empresa, String url, Boolean disponilble) {
        SearchCriteria<Product> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(category)) {
            spec.add(new SearchStatement("category", category, SearchOperation.EQUAL));
        }

        if (price != null) {
            spec.add(new SearchStatement("price", price, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(color)) {
            spec.add(new SearchStatement("color", color, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(peso)) {
            spec.add(new SearchStatement("peso", peso, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(tamanio)) {
            spec.add(new SearchStatement("tamanio", tamanio, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(empresa)) {
            spec.add(new SearchStatement("empresa", empresa, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(url)) {
            spec.add(new SearchStatement("url", url, SearchOperation.MATCH));
        }
        if (disponilble != null) {
            spec.add(new SearchStatement("disponilble", disponilble, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }

}
