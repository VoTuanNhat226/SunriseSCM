package com.vtn.repository;

import com.vtn.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {

    Product findById(Long id);

    void save(Product Product);

    void update(Product Product);

    void delete(Long id);

    Long count();

    List<Product> findAllWithFilter(Map<String, String> params);
}
