package com.example.Shop.service.impl;

import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.model.Cart;
import com.example.Shop.model.CartItem;
import com.example.Shop.model.Product;
import com.example.Shop.repository.CartItemRepository;
import com.example.Shop.repository.CartRepository;
import com.example.Shop.service.CartItemService;
import com.example.Shop.service.CartService;
import com.example.Shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

  private final CartItemRepository cartItemRepository;
  private final ProductService productService;
  private final CartService cartService;
  private final CartRepository cartRepository;

  @Override
  public void addItemToCart(Long cartId, Long productId, int quantity) {
    // 1.Get the cart
    // 2. Get the Product
    // 3. check if the product already in cart
    // 4. if yes, then increase the quantity with the requested quantity
    // 5.if no, then initiate a new cart item entry
    Cart cart = cartService.getCart(cartId);
    Product product = productService.getProductById(productId);

    CartItem cartItem =
        cart.getCartItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .orElse(new CartItem());

    if (cartItem.getId() == null) {
      cartItem.setCart(cart);
      cartItem.setProduct(product);
      cartItem.setQuantity(quantity);
      cartItem.setUnitPrice(product.getPrice());
    } else {
      cartItem.setQuantity(cartItem.getQuantity() + quantity);
    }

    cartItem.setTotalPrice();
    cart.addItem(cartItem);
    cartItemRepository.save(cartItem);
    cartRepository.save(cart);
  }

  @Override
  public void removeItemFromCart(Long cartId, Long productId) {
    Cart cart = cartService.getCart(cartId);
    CartItem cartItem = getCartItem(cartId, productId);

    cart.removeItem(cartItem);
    cartRepository.save(cart);
  }

  @Override
  public void updateItemQuantity(Long cartId, Long productId, int quantity) {
    Cart cart = cartService.getCart(cartId);

    cart.getCartItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .ifPresent(
            item -> {
              item.setQuantity(quantity);
              item.setUnitPrice(item.getProduct().getPrice());
              item.setTotalPrice();
            });

    BigDecimal totalAmount = cart.getTotalAmount();
    cart.setTotalAmount(totalAmount);
    cartRepository.save(cart);
  }

  @Override
  public CartItem getCartItem(Long cartId, Long productId) {
    Cart cart = cartService.getCart(cartId);
    return cart.getCartItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Item Not Found"));
  }
}
