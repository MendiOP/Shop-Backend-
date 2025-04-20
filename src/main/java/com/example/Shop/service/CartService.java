package com.example.Shop.service;

import com.example.Shop.model.Cart;

import java.math.BigDecimal;

public interface CartService {
  Cart getCart(Long id);

  void clearCart(Long id);

  BigDecimal getTotalPrice(Long id);

  Long initializeNewCart();
}
