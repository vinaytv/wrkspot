package com.wrkspot.assessment.modules.customer.application.ports.out;

import com.wrkspot.assessment.modules.customer.domain.data.Customer;

import java.util.List;

public interface CreateCustomerOutPort {

    List<Customer> createCustomer(List<Customer> customer);

}
