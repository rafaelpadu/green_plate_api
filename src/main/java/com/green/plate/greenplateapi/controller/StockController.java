package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.StockDTO;
import com.green.plate.greenplateapi.enums.ProductCategory;
import com.green.plate.greenplateapi.service.stock.impl.StockServiceImpl;
import com.green.plate.greenplateapi.utils.PageFilter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stock",produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {
    private final StockServiceImpl stockService;

    public StockController(StockServiceImpl stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/")
    public ResponseEntity<StockDTO> saveStock(@RequestBody @Valid StockDTO stockDTO) {
        try {
            stockDTO = stockService.saveStock(stockDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(stockDTO);
    }

    @GetMapping("/")
    public List<StockDTO> getAllStock() {
        return stockService.getAllStock();
    }
    @GetMapping("/find-by-product-category/{category}")
    public List<StockDTO> getAllStockByProductCategory(@PathVariable ProductCategory category) {
        return stockService.getAllStockByProductCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Integer id) {
        return stockService
                .getStockById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/search-anything")
    public List<StockDTO> getStockListByAnything(@RequestBody PageFilter pageFilter){
        return stockService.getStockListByAnything(pageFilter);
    }
    @PostMapping("/search-anything-by-store/{storeId}")
    public List<StockDTO> getStockListByStoreIdByAnything(@RequestBody PageFilter pageFilter, @PathVariable Integer storeId){
        return stockService.getStockListByStoreIdByAnything(pageFilter, storeId);
    }
}
