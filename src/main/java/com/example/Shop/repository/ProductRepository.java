package com.example.Shop.repository;

import com.example.Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String productName);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);
}
