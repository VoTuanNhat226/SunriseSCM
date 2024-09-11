package com.vtn.services.implement;

import com.vtn.pojo.Warehouse;
import com.vtn.repository.WarehouseRepository;
import com.vtn.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WarehouseServiceImplement implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(Long id) {
        return this.warehouseRepository.findById(id);
    }

    @Override
    public void save(Warehouse warehouse) {
        this.warehouseRepository.save(warehouse);
    }

    @Override
    public void update(Warehouse warehouse) {
        this.warehouseRepository.update(warehouse);
    }

    @Override
    public void delete(Long id) {
        this.warehouseRepository.delete(id);
    }

    @Override
    public Long count() {
        return this.warehouseRepository.count();
    }

    @Override
    public List<Warehouse> findAllWithFilter(Map<String, String> params) {
        return this.warehouseRepository.findAllWithFilter(params);
    }
}
