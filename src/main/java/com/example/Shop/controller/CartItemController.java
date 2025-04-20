package com.example.Shop.controller;

import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.response.ApiResponse;
import com.example.Shop.service.CartItemService;
import com.example.Shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
  private final CartItemService cartItemService;
  private final CartService cartService;

  @PostMapping("/item/add")
  public ResponseEntity<ApiResponse> addItemToCart(
      @RequestParam(required = false) Long cartId,
      @RequestParam Long productId,
      @RequestParam Integer quantity) {
    try {

      if (cartId == null) cartId = cartService.initializeNewCart();

      cartItemService.addItemToCart(cartId, productId, quantity);
      return new ResponseEntity<>(new ApiResponse("Item added successfully", null), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{cartId}/item/{itemId}/remove")
  public ResponseEntity<ApiResponse> removeItemFromCart(
      @PathVariable Long cartId, @PathVariable Long itemId) {
    try {

      if (cartId == null) cartId = cartService.initializeNewCart();

      cartItemService.removeItemFromCart(cartId, itemId);
      return new ResponseEntity<>(
          new ApiResponse("Item deleted successfully", null), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{cartId}/item/{itemId}/update")
  public ResponseEntity<ApiResponse> updateItemQuantity(
      @PathVariable Long cartId, @PathVariable Long itemId, @RequestParam Integer quantity) {
    try {

      if (cartId == null) cartId = cartService.initializeNewCart();

      cartItemService.updateItemQuantity(cartId, itemId, quantity);
      return new ResponseEntity<>(
          new ApiResponse("Item updated successfully", null), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
  }
}
