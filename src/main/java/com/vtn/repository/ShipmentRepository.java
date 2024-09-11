package com.vtn.repository;

import com.vtn.pojo.Shipment;

import java.util.List;
import java.util.Map;

public interface ShipmentRepository {

    Shipment findById(Long id);

    void save(Shipment shipment);

    void update(Shipment shipment);

    void delete(Long id);

    Long count();

    List<Shipment> findAllWithFilter(Map<String, String> params);
}
