package com.vtn.services.implement;

import com.vtn.pojo.Shipment;
import com.vtn.repository.ShipmentRepository;
import com.vtn.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ShipmentServiceImplement implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public Shipment findById(Long id) {
        return this.shipmentRepository.findById(id);
    }

    @Override
    public void save(Shipment shipment) {
        this.shipmentRepository.save(shipment);
    }

    @Override
    public void update(Shipment shipment) {
        this.shipmentRepository.update(shipment);
    }

    @Override
    public void delete(Long id) {
        this.shipmentRepository.delete(id);
    }

    @Override
    public Long count() {
        return this.shipmentRepository.count();
    }

    @Override
    public List<Shipment> findAllWithFilter(Map<String, String> params) {
        return this.shipmentRepository.findAllWithFilter(params);
    }
}
