package com.example.Shop.service.impl;

import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.model.Cart;
import com.example.Shop.repository.CartItemRepository;
import com.example.Shop.repository.CartRepository;
import com.example.Shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final AtomicLong cartIdGenerator = new AtomicLong(0);

  @Override
  public Cart getCart(Long id) {

    Cart cart =
        cartRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cart Not found"));

    BigDecimal totalAmount = cart.getTotalAmount();
    cart.setTotalAmount(totalAmount);

    return cartRepository.save(cart);
  }

  @Override
  public void clearCart(Long cartId) {
    Cart cart = getCart(cartId);
    cartItemRepository.deleteAllById(cartId);
    cart.getCartItems().clear();
    cartRepository.deleteById(cartId);
  }

  @Override
  public BigDecimal getTotalPrice(Long cartId) {
    Cart cart = getCart(cartId);
    return cart.getTotalAmount();
  }

  @Override
  public Long initializeNewCart(){
    Cart cart = new Cart();
    return cartRepository.save(cart).getId();
  }
}
