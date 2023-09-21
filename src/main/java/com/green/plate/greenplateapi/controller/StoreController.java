package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.ProductDTO;
import com.green.plate.greenplateapi.dto.StoreDTO;
import com.green.plate.greenplateapi.service.store.impl.StoreServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/store/")
public class StoreController {

    private final StoreServiceImpl storeService;

    public StoreController(StoreServiceImpl storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/")
    public ResponseEntity<StoreDTO> saveStore(@RequestBody @Valid StoreDTO storeDTO) {
        try{
            storeService.save(storeDTO);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(storeDTO);
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
    @PutMapping(value = "/store-logo/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<StoreDTO> editStoreLogo(@PathVariable Integer id, @RequestParam("files") MultipartFile[] files){
        try {
            StoreDTO storeDTO = storeService.editStoreLogo(id, files);
            return ResponseEntity.status(HttpStatus.OK).body(storeDTO);
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(ProductController.class);
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
