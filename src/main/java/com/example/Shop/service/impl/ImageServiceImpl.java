package com.example.Shop.service.impl;

import com.example.Shop.dto.ImageDTO;
import com.example.Shop.exceptions.ResourceNotFoundException;
import com.example.Shop.model.Image;
import com.example.Shop.model.Product;
import com.example.Shop.repository.ImageRepository;
import com.example.Shop.service.ImageService;
import com.example.Shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

  private final ImageRepository imageRepository;
  private final ProductService productService;

  @Override
  public Image getImageById(Long id) {
    return imageRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + id));
  }

  @Override
  public void deleteImageById(Long id) {
    imageRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + id));
    imageRepository.deleteById(id);
  }

  @Override
  public List<ImageDTO> saveImage(List<MultipartFile> files, Long productId) {
    Product product = productService.getProductById(productId);
    return files.stream()
        .map(
            image -> {
              try {
                Image newImage = new Image();
                newImage.setFileName(image.getOriginalFilename());
                newImage.setFileType(image.getContentType());
                newImage.setImage(new SerialBlob(image.getBytes()));
                newImage.setProduct(product);
                String downloadUrl = "/api/v1/images/image/download/" + newImage.getId();
                newImage.setDownloadUrl(downloadUrl);

                Image savedImage = imageRepository.save(newImage);
                savedImage.setDownloadUrl("/api/v1/images/image/download/" + savedImage.getId());
                Image newSavedImage = imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageId(newSavedImage.getId());
                imageDTO.setImageName(newSavedImage.getFileName());
                imageDTO.setDownloadUrl(newSavedImage.getDownloadUrl());
                return imageDTO;

              } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
              }
            })
        .toList();
  }

  @Override
  public Image updateImage(MultipartFile file, Long imageId) {
    Image image = getImageById(imageId);

    try {
      image.setFileName(file.getOriginalFilename());
      image.setFileType(file.getContentType());
      image.setImage(new SerialBlob(file.getBytes()));
    } catch (IOException | SQLException e) {
      throw new RuntimeException(e.getMessage());
    }

    return imageRepository.save(image);
  }
}
