package com.green.plate.greenplateapi.service.address;

import com.green.plate.greenplateapi.dto.AddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void save(AddressDTO addressDTO);

    List<AddressDTO> getAllAddress();

    Optional<AddressDTO> getAddressById(Integer id);
}
