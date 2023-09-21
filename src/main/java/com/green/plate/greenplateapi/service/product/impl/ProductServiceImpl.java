package com.green.plate.greenplateapi.service.product.impl;

import com.green.plate.greenplateapi.dto.ProductDTO;
import com.green.plate.greenplateapi.exception.ResourceNotFoundException;
import com.green.plate.greenplateapi.model.Product;
import com.green.plate.greenplateapi.repository.ProductRepository;
import com.green.plate.greenplateapi.service.file.impl.FileServiceImpl;
import com.green.plate.greenplateapi.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileServiceImpl fileService;

    public ProductServiceImpl(ProductRepository productRepository, FileServiceImpl fileService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = mapFromDTO(productDTO);
        productRepository.save(product);
        return mapToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Integer id) {
        return productRepository.findById(id).map(this::mapToDTO);
    }

    @Override
    public ProductDTO editProductImage(Integer productId, MultipartFile[] files) {
        ProductDTO productDTO = getProductById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        String imageUrl = fileService.addSingleFile(files).get(0);
        productDTO.setImageUrl(imageUrl);
        saveProduct(productDTO);
        return productDTO;
    }

    private Product mapFromDTO(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productDTO, Product.class);
    }

    private ProductDTO mapToDTO(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDTO.class);
    }
}
