package com.example.Shop.controller;

import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.model.Cart;
import com.example.Shop.response.ApiResponse;
import com.example.Shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<ApiResponse> getCarts(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return new ResponseEntity<>(new ApiResponse("Carts", cart), HttpStatus.OK);
        } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null ), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return new ResponseEntity<>(new ApiResponse("Cart is cleared", null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse("Cart not found", null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return new ResponseEntity<>(new ApiResponse("Total price", totalPrice), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
}
