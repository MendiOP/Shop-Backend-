package com.example.Shop.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String s) {
    super(s);
  }
}
