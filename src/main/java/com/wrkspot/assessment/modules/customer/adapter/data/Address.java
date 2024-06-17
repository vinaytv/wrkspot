package com.wrkspot.assessment.modules.customer.adapter.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
}
