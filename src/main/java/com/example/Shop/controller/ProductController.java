package com.example.Shop.controller;

import com.example.Shop.dto.ProductDTO;
import com.example.Shop.exceptions.ProductNotFoundException;
import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.model.Product;
import com.example.Shop.request.AddProductRequest;
import com.example.Shop.request.ProductUpdateRequest;
import com.example.Shop.response.ApiResponse;
import com.example.Shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/product")
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<ApiResponse> getAllProducts() {
    List<Product> allProducts = productService.getAllProducts();
    List<ProductDTO> convertedProducts = productService.getConvertedProducts(allProducts);

    return new ResponseEntity<>(new ApiResponse("All Products", convertedProducts), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> createProduct(@RequestBody AddProductRequest product) {
    try {
      Product product1 = productService.addProduct(product);
      return new ResponseEntity<>(new ApiResponse("Product Added", product1), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
    try {
      Product product = productService.getProductById(productId);
      ProductDTO productDTO = productService.convertToProductDTO(product);

      return new ResponseEntity<>(new ApiResponse("Product Details", productDTO), HttpStatus.OK);
    } catch (ProductNotFoundException e) {
      return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{productId}/update")
  public ResponseEntity<ApiResponse> updateProduct(
      @PathVariable Long productId, @RequestBody ProductUpdateRequest product) {
    try {
      Product product1 = productService.updateProduct(productId, product);
      return new ResponseEntity<>(new ApiResponse("Product Updated", product1), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{productId}/delete")
  public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
    try {
      productService.deleteProduct(productId);
      return new ResponseEntity<>(new ApiResponse("Product Deleted", productId), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/brand/name")
  public ResponseEntity<ApiResponse> getProductByBrandAndName(
      @RequestParam String brand, @RequestParam String name) {
    try {
      List<Product> productsByBrandAndName = productService.getProductsByBrandAndName(brand, name);
      List<ProductDTO> convertedProducts = productService.getConvertedProducts(productsByBrandAndName);

      if (convertedProducts.isEmpty())
        return new ResponseEntity<>(
            new ApiResponse("Product is Empty", null), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(
          new ApiResponse("All Products", convertedProducts), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/category/brand")
  public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(
      @RequestParam String category, @RequestParam String brand) {
    try {
      List<Product> productsByCategoryAndName =
          productService.getProductsByCategoryAndBrand(category, brand);

      List<ProductDTO> convertedProducts = productService.getConvertedProducts(productsByCategoryAndName);

      if (convertedProducts.isEmpty())
        return new ResponseEntity<>(
            new ApiResponse("Product is Empty", null), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(
          new ApiResponse("All Products", convertedProducts), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{name}/name")
  public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
    try {
      List<Product> productsByName = productService.getProductsByName(name);
      List<ProductDTO> convertedProducts = productService.getConvertedProducts(productsByName);

      if (convertedProducts.isEmpty())
        return new ResponseEntity<>(
            new ApiResponse("Product is Empty", null), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(new ApiResponse("All Products", convertedProducts), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("{brand}/brand")
  public ResponseEntity<ApiResponse> getProductsByBrand(@PathVariable String brand) {
    try {
      List<Product> productsByCategoryAndName = productService.getProductsByBrand(brand);
      List<ProductDTO> convertedProducts = productService.getConvertedProducts(productsByCategoryAndName);

      if (convertedProducts.isEmpty())
        return new ResponseEntity<>(
            new ApiResponse("Product is Empty", null), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(
          new ApiResponse("All Products", convertedProducts), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{category}/category")
  public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String category) {
    try {
      List<Product> productsByCategoryAndName = productService.getProductsByCategory(category);
      List<ProductDTO> convertedProducts = productService.getConvertedProducts(productsByCategoryAndName);

      if (convertedProducts.isEmpty())
        return new ResponseEntity<>(
            new ApiResponse("Product is Empty", null), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(
          new ApiResponse("All Products", convertedProducts), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/count/brand/name")
  public ResponseEntity<ApiResponse> countProductsByBrandAndName(
      @RequestParam String brand, @RequestParam String name) {
    try {

      Long productCount = productService.countProductsByBrandAndName(brand, name);
      if (productCount == 0)
        return new ResponseEntity<>(
            new ApiResponse("Product is Empty", null), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(
          new ApiResponse("All Products Count", productCount), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
