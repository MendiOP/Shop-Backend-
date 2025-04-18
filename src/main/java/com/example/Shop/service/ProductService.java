package com.example.Shop.service;

import com.example.Shop.dto.ProductDTO;
import com.example.Shop.model.Product;
import com.example.Shop.request.AddProductRequest;
import com.example.Shop.request.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest request);

    Product getProductById(Long id);

    Product updateProduct(Long id, ProductUpdateRequest request);

    void deleteProduct(Long id);

    List<Product> getAllProducts();

    List<ProductDTO> getConvertedProducts(List<Product> products);

    ProductDTO convertToProductDTO(Product product);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByName(String productName);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
