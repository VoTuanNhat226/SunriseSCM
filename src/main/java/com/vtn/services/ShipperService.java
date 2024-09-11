package com.vtn.services;

import com.vtn.dto.shipper.ShipperDTO;
import com.vtn.pojo.Shipper;

import java.util.List;
import java.util.Map;

public interface ShipperService {

    Shipper findById(Long id);

    void save(Shipper shipper);

    void update(Shipper shipper);

    void delete(Long id);

    Long count();

    List<Shipper> findAllWithFilter(Map<String, String> params);

    ShipperDTO getShipperResponse(Shipper shipper);

    Shipper getProfileShipper(String username);

    ShipperDTO updateProfileShipper(String username, ShipperDTO shipperDTO);
}
