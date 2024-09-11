package com.vtn.repository;

import com.vtn.pojo.Shipper;
import com.vtn.pojo.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface ShipperRepository {

    Shipper findById(Long id);

    Shipper findByUser(@NotNull User user);

    void save(Shipper shipper);

    void update(Shipper shipper);

    void delete(Long id);

    Long count();

    List<Shipper> findAllWithFilter(Map<String, String> params);
}
