package com.green.plate.greenplateapi.service.stock;

import com.green.plate.greenplateapi.dto.StockDTO;
import com.green.plate.greenplateapi.enums.ProductCategory;
import com.green.plate.greenplateapi.utils.PageFilter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StockService {
    StockDTO saveStock(StockDTO stockDTO);

    List<StockDTO> getAllStock();

    Optional<StockDTO> getStockById(Integer id);

    List<StockDTO> getAllStockByProductCategory(ProductCategory category);

    List<StockDTO> getStockListByAnything(PageFilter pageFilter);

    List<StockDTO> getStockListByStoreIdByAnything(PageFilter pageFilter, Integer storeId);
}
