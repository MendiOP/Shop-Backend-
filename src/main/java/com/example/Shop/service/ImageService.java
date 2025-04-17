package com.example.Shop.service;

import com.example.Shop.dto.ImageDTO;
import com.example.Shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
  Image getImageById(Long id);

  void deleteImageById(Long id);

  List<ImageDTO> saveImage(List<MultipartFile> files, Long productId);

  Image updateImage(MultipartFile file, Long imageId);
}
