package com.example.Shop.service;

import com.example.Shop.model.CartItem;

public interface CartItemService {
  void addItemToCart(Long cartId, Long productId, int quantity);

  void removeItemFromCart(Long cartId, Long productId);

  void updateItemQuantity(Long cartId, Long productId, int quantity);

  CartItem getCartItem(Long cartId, Long productId);
}
