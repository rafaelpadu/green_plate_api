package com.green.plate.greenplateapi.service.stock.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.plate.greenplateapi.dto.ProductDTO;
import com.green.plate.greenplateapi.dto.StockDTO;
import com.green.plate.greenplateapi.enums.ProductCategory;
import com.green.plate.greenplateapi.exception.ResourceNotFoundException;
import com.green.plate.greenplateapi.model.Product;
import com.green.plate.greenplateapi.model.Stock;
import com.green.plate.greenplateapi.repository.ProductRepository;
import com.green.plate.greenplateapi.repository.StockPageableRepository;
import com.green.plate.greenplateapi.repository.StockRepository;
import com.green.plate.greenplateapi.service.stock.StockService;
import com.green.plate.greenplateapi.utils.PageFilter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockPageableRepository stockPageableRepository;

    public StockServiceImpl(StockRepository stockRepository,
                            ProductRepository productRepository,
                            StockPageableRepository stockPageableRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.stockPageableRepository = stockPageableRepository;
    }

    @Override
    public StockDTO saveStock(StockDTO stockDTO) {
        Stock stock = mapToStock(stockDTO);
        productRepository.findById(stock.getProduct().getId()).orElseThrow(() -> new ResourceNotFoundException("Produto não existente"));
        return mapToStockDTO(stockRepository.save(stock));
    }

    @Override
    public List<StockDTO> getAllStock() {
        return stockRepository.findAll().stream().map(this::mapToStockDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StockDTO> getStockById(Integer id) {
        return stockRepository.findById(id).map(this::mapToStockDTO);
    }

    @Override
    public List<StockDTO> getAllStockByProductCategory(ProductCategory category) {
        return stockRepository.findByProduct_ProductCategory(category).stream().map(this::mapToStockDTO).collect(Collectors.toList());

    }

    @Override
    public List<StockDTO> getStockListByAnything(PageFilter pageFilter) {
        List<StockDTO> stockDTOList;
        Pageable pageable = PageRequest.of(pageFilter.getPageNumber(), pageFilter.getPageSize());
        if(pageFilter.getQueryText() == null){
            stockDTOList = stockPageableRepository.findAll(pageable).stream().map(this::mapToStockDTO).toList();
        }else{
            stockDTOList = stockPageableRepository.findStockByAnything(pageFilter.getQueryText(), pageable).stream().map(this::mapToStockDTO).collect(Collectors.toList());
        }
        return stockDTOList;
    }

    private Stock mapToStock(StockDTO stockDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(stockDTO, Stock.class);
    }

    private StockDTO mapToStockDTO(Stock stock) {
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(stock.getProduct(), ProductDTO.class);
        StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
        stockDTO.setProductDTO(productDTO);
        return stockDTO;
    }
}
