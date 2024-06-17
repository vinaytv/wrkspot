package com.wrkspot.assessment.modules.customer.adapter.in.web;


import com.wrkspot.assessment.modules.customer.application.ports.in.CreateCustomerInPort;
import com.wrkspot.assessment.modules.customer.application.ports.in.SearchCustomerInPort;
import com.wrkspot.assessment.modules.customer.domain.data.Customer;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerFilters;
import com.wrkspot.assessment.util.CustomPageableRequest;
import com.wrkspot.assessment.util.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerInPort createCustomerInPort;

    private final SearchCustomerInPort searchCustomerInPort;

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody List<Customer> customerList) {
        RequestValidator.validateCreateCustomerRequest(customerList);
        return new ResponseEntity<>(createCustomerInPort.createCustomer(customerList), HttpStatus.CREATED);
    }


    @GetMapping("/customer")
    public ResponseEntity<?> searchCustomers(CustomerFilters customerFilters, Pageable pageable) {
        CustomPageableRequest customPageableRequest = new CustomPageableRequest(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return new ResponseEntity<>(searchCustomerInPort.searchCustomer(customerFilters, customPageableRequest), HttpStatus.OK);

    }


}
