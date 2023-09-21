package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.ProductDTO;
import com.green.plate.greenplateapi.service.product.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        try {
            productDTO = productService.saveProduct(productDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return productService
                .getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/product-image/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductDTO> editProductImage(@PathVariable Integer id, @RequestParam("files") MultipartFile[] files) {
        try {
            ProductDTO productDTO = productService.editProductImage(id, files);
            return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(ProductController.class);
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
