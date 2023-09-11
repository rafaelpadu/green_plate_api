package com.green.plate.greenplateapi.service.address.impl;

import com.green.plate.greenplateapi.dto.AddressDTO;
import com.green.plate.greenplateapi.model.Address;
import com.green.plate.greenplateapi.repository.AddressRepository;
import com.green.plate.greenplateapi.service.address.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<AddressDTO> getAllAddress() {
        return addressRepository
                .findAll()
                .stream()
                .map(this::mapToAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AddressDTO> getAddressById(Integer id) {
        return addressRepository.findById(id).map(this::mapToAddressDTO);
    }

    private Address mapToAddress(AddressDTO addressDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(addressDTO, Address.class);
    }

    private AddressDTO mapToAddressDTO(Address address) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(address, AddressDTO.class);
    }
}
