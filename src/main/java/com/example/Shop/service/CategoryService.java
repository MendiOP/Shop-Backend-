package com.example.Shop.service;

import com.example.Shop.model.Category;

import java.util.List;

public interface CategoryService {
  Category getCategoryById(Long id);

  Category getCategoryByName(String name);

  List<Category> getAllCategories();

  Category addCategory(Category category);

  Category updateCategory(Long id, Category category);

  void deleteCategory(Long id);
}
