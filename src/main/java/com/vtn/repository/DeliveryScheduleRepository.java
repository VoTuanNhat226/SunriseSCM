package com.vtn.repository;

import com.vtn.pojo.DeliverySchedule;

import java.util.List;
import java.util.Map;

public interface DeliveryScheduleRepository {

    DeliverySchedule findById(Long id);

    void save(DeliverySchedule deliverySchedule);

    void update(DeliverySchedule deliverySchedule);

    void delete(Long id);

    Long count();

    List<DeliverySchedule> findAllWithFilter(Map<String, String> params);
}
