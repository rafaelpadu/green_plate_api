package com.green.plate.greenplateapi.service.store.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.plate.greenplateapi.dto.StoreDTO;
import com.green.plate.greenplateapi.exception.ResourceNotFoundException;
import com.green.plate.greenplateapi.model.Store;
import com.green.plate.greenplateapi.repository.StoreRepository;
import com.green.plate.greenplateapi.service.file.impl.FileServiceImpl;
import com.green.plate.greenplateapi.service.store.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final FileServiceImpl fileService;

    public StoreServiceImpl(StoreRepository storeRepository, FileServiceImpl fileService) {
        this.storeRepository = storeRepository;
        this.fileService = fileService;
    }

    @Override
    public StoreDTO save(StoreDTO storeDTO) {
        Store store = mapStoreDTO(storeDTO);
        return mapStore(storeRepository.save(store));
    }

    @Override
    public List<StoreDTO> getAll() {
        List<Store> storeList = storeRepository.findAll();
        return storeList.stream().map(this::mapStore).collect(Collectors.toList());
    }

    @Override
    public Optional<StoreDTO> getStoreById(Integer id) {
        return storeRepository.findById(id).map(this::mapStore);
    }

    @Override
    public StoreDTO editStoreLogo(Integer id, MultipartFile[] files) {
        StoreDTO storeDTO = getStoreById(id).orElseThrow(() -> new ResourceNotFoundException("Loja não encontrada"));
        String imageUrl = fileService.addSingleFile(files).get(0);
        storeDTO.setLogoImgUrl(imageUrl);
        return save(storeDTO);
    }

    private Store mapStoreDTO(StoreDTO storeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storeDTO, Store.class);
    }

    private StoreDTO mapStore(Store store) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreDTO.class);
    }
}
