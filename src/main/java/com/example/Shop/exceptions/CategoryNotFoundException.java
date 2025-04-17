package com.example.Shop.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String noCategoryFound) {
        super(noCategoryFound);
    }
}
