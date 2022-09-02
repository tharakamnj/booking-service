package com.booking.controller;

import com.booking.dto.ReserveRequest;
import com.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody ReserveRequest request){
        return bookingService.createBooking(request);
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBookingbyId(@PathVariable Long id){
        return bookingService.getById(id);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id,@RequestBody ReserveRequest request){
        request.setBookingId(id);
        return bookingService.updateBookig(request);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id){
        return bookingService.deleteBooking(id);
    }

    /*@GetMapping("bookings/cus/{cusId}")
    public ResponseEntity<?> getBookingsByCusId(@PathVariable Long cusId){
        return bookingService.bookingGetByCutId(cusId);
    }*/
}