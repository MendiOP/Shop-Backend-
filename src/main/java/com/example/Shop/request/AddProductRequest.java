package com.example.Shop.request;

import com.example.Shop.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private Long inventory;
    private String brand;
    private Category category;
}
