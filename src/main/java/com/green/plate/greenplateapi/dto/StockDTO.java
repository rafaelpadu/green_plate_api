package com.green.plate.greenplateapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.plate.greenplateapi.model.Stock;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Stock}
 */
@Data
public class StockDTO implements Serializable {
    Integer id;
    Date createdAt;
    Date updatedAt;
    Integer storeId;
    String storeTradeName;
    ProductDTO productDTO;
    Integer currentQty;
    Integer minimalQty;
    Integer maxQty;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime dueDate;
    List<PriceDTO> priceList;
}
