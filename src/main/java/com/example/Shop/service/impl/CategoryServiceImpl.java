package com.example.Shop.service.impl;

import com.example.Shop.exceptions.AlreadyExistsException;
import com.example.Shop.exceptions.CategoryNotFoundException;
import com.example.Shop.model.Category;
import com.example.Shop.repository.CategoryRepository;
import com.example.Shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category getCategoryById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("No category found"));
  }

  @Override
  public Category getCategoryByName(String name) {
    return categoryRepository.findByName(name);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category addCategory(Category category) {
    return Optional.of(category)
        .filter(old -> !categoryRepository.existsByName(old.getName()))
        .map(categoryRepository::save)
        .orElseThrow(() -> new AlreadyExistsException(category.getName() + " already exists"));
  }

  @Override
  public Category updateCategory(Long id, Category category) {
    return Optional.ofNullable(getCategoryById(id))
        .map(
            old -> {
              old.setName(category.getName());
              return categoryRepository.save(old);
            })
        .orElseThrow(() -> new CategoryNotFoundException("No category found"));
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository
        .findById(id)
        .ifPresentOrElse(
            categoryRepository::delete,
            () -> {
              throw new CategoryNotFoundException("No category found");
            });
  }
}
