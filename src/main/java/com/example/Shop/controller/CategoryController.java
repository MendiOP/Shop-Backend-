package com.example.Shop.controller;

import com.example.Shop.model.Category;
import com.example.Shop.response.ApiResponse;
import com.example.Shop.service.CategoryService;
import com.example.Shop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        try{
            List<Category> allCategories = categoryService.getAllCategories();

            return new ResponseEntity<>(new ApiResponse("All Categories", allCategories), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse("Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
