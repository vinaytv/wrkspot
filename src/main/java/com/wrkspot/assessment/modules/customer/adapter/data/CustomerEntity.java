package com.wrkspot.assessment.modules.customer.adapter.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_details")
@NoArgsConstructor
public class CustomerEntity implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "spending_limit")
    private Double spendingLimit;
    @Column(name = "mobile")
    private String mobileNumber;
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
    @Column(name = "created_by")
    private String createdBy = "SYSTEM";
    @Column(name = "modified_at")
    private Instant modifiedAt;
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Address> address;


}
