package com.green.plate.greenplateapi.service.product;

import com.green.plate.greenplateapi.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProduct();

    Optional<ProductDTO> getProductById(Integer id);

    ProductDTO editProductImage(Integer productId, MultipartFile[] files);
}
