package com.booking.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private Long cusId;
    private String userName;
    private String email;
    private String mobile;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Booking> bookings;

    public Customer(Long cusId, String userName, String email, String mobile) {
        this.cusId = cusId;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
    }

    public Customer(String userName, String email, String mobile) {
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
    }
}
