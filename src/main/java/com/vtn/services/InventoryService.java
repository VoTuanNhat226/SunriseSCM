package com.vtn.services;

import com.vtn.pojo.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    Inventory findById(Long id);

    void save(Inventory inventory);

    void update(Inventory inventory);

    void delete(Long id);

    Long count();

    List<Inventory> findAllWithFilter(Map<String, String> params);
}
