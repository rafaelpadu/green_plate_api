package com.green.plate.greenplateapi.service.stock.impl;

import com.green.plate.greenplateapi.dto.StockDTO;
import com.green.plate.greenplateapi.model.Stock;
import com.green.plate.greenplateapi.repository.StockRepository;
import com.green.plate.greenplateapi.service.stock.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDTO saveStock(StockDTO stockDTO) {
        Stock stock = mapToStock(stockDTO);
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

    private Stock mapToStock(StockDTO stockDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(stockDTO, Stock.class);
    }

    private StockDTO mapToStockDTO(Stock stock) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(stock, StockDTO.class);
    }
}
