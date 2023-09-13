package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.PriceDTO;
import com.green.plate.greenplateapi.service.price.impl.PriceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    private final PriceServiceImpl priceService;

    public PriceController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/")
    public ResponseEntity<PriceDTO> savePrice(@RequestBody @Valid PriceDTO priceDTO) {
        try {
            priceDTO = priceService.savePrice(priceDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(priceDTO);
    }

    @GetMapping("/")
    public List<PriceDTO> getAllPrice() {
        return priceService.getAllPrice();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable Integer id) {
        return priceService
                .getPriceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
