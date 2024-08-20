package com.vtn.repository;

import com.vtn.pojo.OrderDetail;

public interface OrderDetailRepository {
    void saveOrUpdate(OrderDetail orderDetail);
    OrderDetail findById(Long id);
}
