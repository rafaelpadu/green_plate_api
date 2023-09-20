package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.AddressDTO;
import com.green.plate.greenplateapi.service.address.AddressService;
import com.green.plate.greenplateapi.service.address.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public List<AddressDTO> getAllAddress(){
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressByID(@PathVariable Integer id ){
        return addressService
                .getAddressById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
