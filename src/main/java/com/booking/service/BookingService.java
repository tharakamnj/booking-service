package com.booking.service;

import com.booking.constant.ValidationMessages;
import com.booking.dto.CustomerDTO;
import com.booking.dto.ReserveRequest;
import com.booking.dto.ReserveResponse;
import com.booking.entity.Booking;
import com.booking.entity.Customer;
import com.booking.repository.BookingRepository;
import com.booking.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    private BookingRepository bookingRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> createBooking(ReserveRequest request){
        if (!customerRepository.existsById(request.getCusId())){
            return new ResponseEntity<>(request.getCusId().toString()+"Customer"+ ValidationMessages.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        Customer customer = customerRepository.findById(request.getCusId()).get();
        Long bookingId = bookingRepository.save(new Booking(
                request.getHotelName(),
                request.getRoomNumber(),
                request.getRoomType(),
                request.getCheckIn(),
                request.getCheckOut(),customer)).getBookingId();
        logger.debug(bookingId+"booking created...");
        return new ResponseEntity<>(bookingId.toString()+ValidationMessages.SUCCESS,HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long id){
        if (!bookingRepository.existsById(id)){
            return new ResponseEntity<>(ValidationMessages.NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        Booking booking = bookingRepository.findById(id).get();
        //booking.setCustomer(booking.getCustomer());
        ReserveResponse response = new ReserveResponse(
                booking.getBookingId(),
                booking.getHotelName(),
                booking.getRoomNumber(),
                booking.getRoomType(),
                booking.getCheckIn(),
                booking.getCheckOut());
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    public ResponseEntity<?> updateBookig(ReserveRequest request){
        if (!bookingRepository.existsById(request.getBookingId())){
            logger.debug("not found booking "+request.getBookingId());
            return new ResponseEntity<>(ValidationMessages.NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        Customer customer = customerRepository.findById(request.getCusId()).get();
        Booking booking = bookingRepository.save(new Booking(
                request.getBookingId(),
                request.getHotelName(),
                request.getRoomNumber(),
                request.getRoomType(),
                request.getCheckIn(),
                request.getCheckOut(),
                customer));

        logger.debug(booking.getBookingId()+"booking succesfully updated..");
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)){
            logger.debug("not found booking"+id);
            return new ResponseEntity<>(ValidationMessages.NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        bookingRepository.deleteById(id);
        logger.debug("Successfully deleted.."+id);
        return new ResponseEntity<>(id+" deleted",HttpStatus.OK);
    }

    /*public ResponseEntity<?> bookingGetByCutId(Long cusId){
        List<Booking> bookings = bookingRepository.findBookingByCusId(cusId);
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }*/
}