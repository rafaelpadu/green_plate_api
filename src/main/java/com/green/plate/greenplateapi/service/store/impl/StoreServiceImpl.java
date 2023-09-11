package com.green.plate.greenplateapi.service.store.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.plate.greenplateapi.dto.StoreDTO;
import com.green.plate.greenplateapi.model.Store;
import com.green.plate.greenplateapi.repository.StoreRepository;
import com.green.plate.greenplateapi.service.store.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void save(StoreDTO storeDTO) {
        Store store = mapStoreDTO(storeDTO);
        storeRepository.save(store);
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

    private Store mapStoreDTO(StoreDTO storeDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storeDTO, Store.class);
    }
    private StoreDTO mapStore(Store store){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(store, StoreDTO.class);
    }
}
