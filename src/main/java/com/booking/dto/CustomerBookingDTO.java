package com.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBookingDTO {

    private CustomerDTO customer;
    private List<ReserveResponse> reserveResponses;
}
