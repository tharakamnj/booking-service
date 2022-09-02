package com.booking.dto;

import com.booking.constant.RoomType;
import com.booking.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class
ReserveRequest {

    private Long bookingId;
    private String hotelName;
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Long cusId;
}
