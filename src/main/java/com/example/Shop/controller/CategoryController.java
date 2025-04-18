package com.example.Shop.controller;

import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.model.Category;
import com.example.Shop.response.ApiResponse;
import com.example.Shop.service.CategoryService;
import com.example.Shop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<ApiResponse> getAllCategories() {
    try {
      List<Category> allCategories = categoryService.getAllCategories();

      return new ResponseEntity<>(new ApiResponse("All Categories", allCategories), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
    try {
      Category category1 = categoryService.addCategory(category);
      return new ResponseEntity<>(new ApiResponse("Category Added", category1), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse("Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/category/{categoryId}/get")
  public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
    try {
      Category category = categoryService.getCategoryById(categoryId);
      return new ResponseEntity<>(new ApiResponse("Category Found", category), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Category Not Found", e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/category/{name}/name")
  public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
    try {
      Category category = categoryService.getCategoryByName(name);
      return new ResponseEntity<>(new ApiResponse("Category Found", category), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Category Not Found", e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/category/{categoryId}/delete")
  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
    try {
      categoryService.deleteCategory(categoryId);
      return new ResponseEntity<>(new ApiResponse("Category Deleted", null), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Error Deleting Category", e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/category/{categoryId}/update")
  public ResponseEntity<ApiResponse> updateCategory(
      @PathVariable Long categoryId, @RequestBody Category category) {
    try {
      Category category1 = categoryService.updateCategory(categoryId, category);
      return new ResponseEntity<>(new ApiResponse("Category Updated", category1), HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Error Updating Category", e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }
}
