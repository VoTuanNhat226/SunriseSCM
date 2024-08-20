package com.vtn.service;

import com.vtn.dto.order.request.OrderDetailRequestDTO;
import com.vtn.dto.order.request.OrderRequestDTO;
import com.vtn.dto.order.response.OrderDetailResponseDTO;
import com.vtn.dto.order.response.OrderResponseDTO;
import com.vtn.dto.user.request.UserRequestDTO;
import com.vtn.pojo.Order;
import com.vtn.pojo.OrderDetail;

import java.util.List;

public interface OrderService {
    OrderResponseDTO addOrder(OrderRequestDTO orderRequest);
    OrderResponseDTO confirmOrder(Long orderId, Long userId);
    boolean cancelOrder(Long orderId, Long userId);
    OrderResponseDTO updateOrder(OrderRequestDTO orderRequest);
    OrderDetailResponseDTO addOrderDetail(Long orderId, OrderDetailRequestDTO orderDetailRequest);
    void deleteOrder(Long orderId);
    OrderResponseDTO getOrder(Long orderId);
    List<OrderResponseDTO> getAllOrders();
}
