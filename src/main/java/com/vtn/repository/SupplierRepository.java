package com.vtn.repository;

import com.vtn.pojo.Supplier;
import com.vtn.pojo.User;

import java.util.List;
import java.util.Map;

public interface SupplierRepository {

    Supplier findById(Long id);

    Supplier findByUser(User user);

    void save(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);

    Long count();

    List<Supplier> findAllWithFilter(Map<String, String> params);
}
