package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.AddressDTO;
import com.green.plate.greenplateapi.service.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody AddressDTO addressDTO){
        addressService.save(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
