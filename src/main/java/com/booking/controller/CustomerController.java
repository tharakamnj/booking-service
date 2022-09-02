package com.booking.controller;

import com.booking.dto.CustomerDTO;
import com.booking.service.CustomerService;
import com.booking.util.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO dto){
        return customerService.saveCustomer(dto);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CommonResponse> getCustomerById(@PathVariable("id") int id){
        return customerService.findCusById(id);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCust(){
        return customerService.findAllCustomer();
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCust(@PathVariable("id") Long id, @RequestBody CustomerDTO dto){
        dto.setCusId(id);
        return customerService.updateCustomer(dto);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCust(@PathVariable("id") Long id){
        return customerService.deleteCustomer(id);
    }

}
