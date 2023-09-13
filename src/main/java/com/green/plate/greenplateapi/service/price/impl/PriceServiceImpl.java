package com.green.plate.greenplateapi.service.price.impl;

import com.green.plate.greenplateapi.dto.PriceDTO;
import com.green.plate.greenplateapi.model.Price;
import com.green.plate.greenplateapi.repository.PriceRepository;
import com.green.plate.greenplateapi.service.price.PriceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceDTO savePrice(PriceDTO priceDTO) {
        return mapToPriceDTO(priceRepository.save(mapToPrice(priceDTO)));
    }

    @Override
    public List<PriceDTO> getAllPrice() {
        return priceRepository.findAll().stream().map(this::mapToPriceDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<PriceDTO> getPriceById(Integer id) {
        return priceRepository.findById(id).map(this::mapToPriceDTO);
    }

    private Price mapToPrice(PriceDTO priceDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(priceDTO, Price.class);
    }

    private PriceDTO mapToPriceDTO(Price price) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(price, PriceDTO.class);
    }
}
