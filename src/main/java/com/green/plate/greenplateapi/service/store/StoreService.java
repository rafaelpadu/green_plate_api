package com.green.plate.greenplateapi.service.store;

import com.green.plate.greenplateapi.dto.StoreDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    StoreDTO save(StoreDTO StoreDTO);

    List<StoreDTO> getAll();

    Optional<StoreDTO> getStoreById(Integer id);

    StoreDTO editStoreLogo(Integer id, MultipartFile[] files);
}
