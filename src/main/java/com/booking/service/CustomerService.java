package com.booking.service;

import com.booking.constant.ValidationMessages;
import com.booking.dto.CustomerDTO;
import com.booking.dto.ReserveResponse;
import com.booking.entity.Booking;
import com.booking.entity.Customer;
import com.booking.repository.CustomerRepository;
import com.booking.util.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> saveCustomer(CustomerDTO dto){
        if (!customerRepository.existsById(dto.getCusId())){
            customerRepository.save(new Customer(
                    dto.getCusId(),
                    dto.getUserName(),
                    dto.getEmail(),
                    dto.getMobile()));
            return new ResponseEntity<>("Successfully created. "+dto.getCusId(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(ValidationMessages.ALREADY_EXIST,HttpStatus.ALREADY_REPORTED);
    }

    public ResponseEntity<?> findAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    public ResponseEntity<CommonResponse> findCusById(int cusId){
        CommonResponse commonResponse = new CommonResponse();
        if (!customerRepository.existsById(Long.valueOf(cusId))){
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessage(ValidationMessages.NOT_FOUND);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        }
        Customer customer = customerRepository.findById(Long.valueOf(cusId)).get();
        Map<String,Object> map = new HashMap<>();
        CustomerDTO customerNew = new CustomerDTO(customer.getCusId(), customer.getUserName(), customer.getEmail(), customer.getMobile());
        List<ReserveResponse> reserveList = new ArrayList<>();
        //List<Booking> customerList = customer.getBookings();
        for (Booking booking : customer.getBookings()) {
            ReserveResponse reserve = new ReserveResponse(
                    booking.getBookingId(),
                    booking.getHotelName(),
                    booking.getRoomNumber(),
                    booking.getRoomType(),
                    booking.getCheckIn(),
                    booking.getCheckOut());
            reserveList.add(reserve);
        }
        map.put("customer",customerNew);
        map.put("bookings",reserveList);
        commonResponse.setStatus(1);
        commonResponse.setErrorMessage(ValidationMessages.SUCCESS);
        commonResponse.setPayload(map);
        //return new ResponseEntity<>(customer,HttpStatus.OK);
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    public ResponseEntity<?> updateCustomer(CustomerDTO customerDTO){
        if (!customerRepository.existsById(customerDTO.getCusId())){
            return new ResponseEntity<>(ValidationMessages.NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        customerRepository.save(new Customer(
                customerDTO.getCusId(),
                customerDTO.getUserName(),
                customerDTO.getEmail(),
                customerDTO.getMobile()));
        return new ResponseEntity<>(customerDTO.getCusId()+" Successfully updated",HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCustomer(Long id){
        if (!customerRepository.existsById(id)){
            return new ResponseEntity<>(ValidationMessages.NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        customerRepository.deleteById(id);
        return new ResponseEntity<>(ValidationMessages.SUCCESS,HttpStatus.OK);

    }

}
