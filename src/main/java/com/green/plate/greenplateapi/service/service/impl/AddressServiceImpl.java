package com.green.plate.greenplateapi.service.service.impl;

import com.green.plate.greenplateapi.dto.AddressDTO;
import com.green.plate.greenplateapi.model.Address;
import com.green.plate.greenplateapi.repository.AddressRepository;
import com.green.plate.greenplateapi.service.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(AddressDTO addressDTO) {
        Address address = mapToAddress(addressDTO);
        addressRepository.save(address);
    }

    private Address mapToAddress(AddressDTO addressDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(addressDTO, Address.class);
    }
}
