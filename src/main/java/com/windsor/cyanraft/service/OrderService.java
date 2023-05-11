package com.windsor.cyanraft.service;

import com.windsor.cyanraft.dto.CreateOrderRequest;
import com.windsor.cyanraft.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
