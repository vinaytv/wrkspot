package com.wrkspot.assessment.modules.customer.application.usecases;

import com.wrkspot.assessment.modules.customer.application.ports.in.SearchCustomerInPort;
import com.wrkspot.assessment.modules.customer.application.ports.out.SearchCustomerOutPort;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerFilters;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerResponse;
import com.wrkspot.assessment.util.CustomPageableRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchCustomerUseCase implements SearchCustomerInPort {

    private final SearchCustomerOutPort searchCustomerOutPort;

    @Override
    public CustomerResponse searchCustomer(CustomerFilters customerFilters, CustomPageableRequest customPageableRequest) {
        return searchCustomerOutPort.searchCustomer(customerFilters, customPageableRequest);
    }
}
