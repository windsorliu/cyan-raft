package com.windsor.cyanraft.service;

import com.windsor.cyanraft.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
