package com.example.Shop.repository;

import com.example.Shop.dto.ProductDTO;
import com.example.Shop.model.Image;
import com.example.Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
