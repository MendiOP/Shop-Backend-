package com.example.Shop.controller;

import com.example.Shop.dto.ImageDTO;
import com.example.Shop.exceptions.ImageNotFoundException;
import com.example.Shop.model.Image;
import com.example.Shop.response.ApiResponse;
import com.example.Shop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images")
public class ImageController {

  private final ImageService imageService;

  @PostMapping
  public ResponseEntity<ApiResponse> saveImage(
      @RequestParam List<MultipartFile> file, @RequestParam Long productId) {
    try {
      List<ImageDTO> imageDTOS = imageService.saveImage(file, productId);
      return new ResponseEntity<>(
          new ApiResponse("Upload Successfully!", imageDTOS), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ApiResponse("Upload Failed!", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/${api.prefix}/image/download/{imageId}")
  public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
    Image imageById = imageService.getImageById(imageId);

    ByteArrayResource byteArrayResource =
        new ByteArrayResource(
            imageById.getImage().getBytes(1, (int) imageById.getImage().length()));

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(imageById.getFileType()))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + imageById.getFileName() + "\"")
        .body(byteArrayResource);
  }

  @PutMapping("${api.prefix}/image/{imageId}/update")
  public ResponseEntity<ApiResponse> updateImage(
      @PathVariable Long imageId, @RequestBody MultipartFile file) {
    try {
      Image image = imageService.getImageById(imageId);

      if (image != null) {
        Image image1 = imageService.updateImage(file, imageId);
        return new ResponseEntity<>(new ApiResponse("Update Successfully!", image1), HttpStatus.OK);
      }
    } catch (ImageNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Update Failed!", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(
        new ApiResponse("Update Failed!", " Image not found"), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("${api.prefix}/image/{imageId}/delete")
  public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
    try {
      Image image = imageService.getImageById(imageId);

      if (image != null) {
        imageService.deleteImageById(imageId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully!", null), HttpStatus.OK);
      }
    } catch (ImageNotFoundException e) {
      return new ResponseEntity<>(
          new ApiResponse("Delete Failed!", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(
        new ApiResponse("Delete Failed!", " Image not found"), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
