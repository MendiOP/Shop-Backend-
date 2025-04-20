package com.example.Shop.dto;

import com.example.Shop.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long inventory;
    private String brand;
    private Category category;
    private List<ImageDTO> images;
}
