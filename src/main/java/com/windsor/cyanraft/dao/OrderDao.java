package com.windsor.cyanraft.dao;

import com.windsor.cyanraft.dto.CreateOrderRequest;
import com.windsor.cyanraft.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
