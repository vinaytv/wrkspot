package com.wrkspot.assessment.modules.customer.application.usecases;

import com.wrkspot.assessment.modules.customer.application.ports.in.CreateCustomerInPort;
import com.wrkspot.assessment.modules.customer.application.ports.out.CreateCustomerOutPort;
import com.wrkspot.assessment.modules.customer.domain.data.Customer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateCustomerUseCase implements CreateCustomerInPort {
    private final CreateCustomerOutPort createCustomerOutPort;

    @Override
    public List<Customer> createCustomer(List<Customer> customer) {
        return createCustomerOutPort.createCustomer(customer);
    }
}
