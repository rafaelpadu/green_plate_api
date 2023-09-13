package com.green.plate.greenplateapi.service.price;

import com.green.plate.greenplateapi.dto.PriceDTO;

import java.util.List;
import java.util.Optional;

public interface PriceService {
    PriceDTO savePrice(PriceDTO priceDTO);

    List<PriceDTO> getAllPrice();

    Optional<PriceDTO> getPriceById(Integer id);
}
