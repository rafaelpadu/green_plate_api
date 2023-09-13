package com.green.plate.greenplateapi.service.stock;

import com.green.plate.greenplateapi.dto.StockDTO;

import java.util.List;
import java.util.Optional;

public interface StockService {
    StockDTO saveStock(StockDTO stockDTO);

    List<StockDTO> getAllStock();

    Optional<StockDTO> getStockById(Integer id);
}
