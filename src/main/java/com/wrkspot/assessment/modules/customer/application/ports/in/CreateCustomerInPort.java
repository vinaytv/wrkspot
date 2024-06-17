package com.wrkspot.assessment.modules.customer.application.ports.in;

import com.wrkspot.assessment.modules.customer.domain.data.Customer;

import java.util.List;

public interface CreateCustomerInPort {

    List<Customer> createCustomer(List<Customer> customer);

}
