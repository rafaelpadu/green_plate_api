package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.StoreDTO;
import com.green.plate.greenplateapi.service.store.impl.StoreServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store/")
public class StoreController {

    private final StoreServiceImpl storeService;

    public StoreController(StoreServiceImpl storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/")
    public ResponseEntity<String> saveStore(@RequestBody StoreDTO storeDTO) {
        storeService.save(storeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<StoreDTO> getAllStore() {
        return storeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Integer id) {
        return storeService
                .getStoreById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
