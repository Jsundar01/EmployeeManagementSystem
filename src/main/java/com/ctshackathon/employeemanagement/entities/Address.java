package com.ctshackathon.employeemanagement.entities;

import com.ctshackathon.employeemanagement.dto.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String pincode;

    public Address(AddressDTO address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.pincode = address.getPincode();
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " - " + pincode;
    }
}
