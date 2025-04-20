package com.example.Shop.service;

import com.example.Shop.dto.OrderDto;
import com.example.Shop.model.Order;

import java.util.List;

public interface OrderService {
  Order placeOrder(Long userId);

  OrderDto getOrder(Long orderId);

  List<OrderDto> getUserOrders(Long userId);
}
