package com.booking.entity;

import com.booking.constant.RoomType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private String hotelName;
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @ManyToOne
    @JsonBackReference
    private Customer customer;

    public Booking(String hotelName, String roomNumber, RoomType roomType, LocalDate checkIn, LocalDate checkOut, Customer customer) {
        this.hotelName = hotelName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.customer = customer;
    }
}