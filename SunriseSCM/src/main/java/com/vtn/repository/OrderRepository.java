package com.vtn.repository;

import com.vtn.pojo.Order;

import java.util.List;

public interface OrderRepository {
    void saveOrUpdate(Order order);
    void delete(Order order);
    Order findById(Long id);
    List<Order> findAll();
}
