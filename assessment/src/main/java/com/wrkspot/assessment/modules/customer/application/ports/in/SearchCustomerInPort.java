package com.wrkspot.assessment.modules.customer.application.ports.in;

import com.wrkspot.assessment.modules.customer.domain.data.CustomerFilters;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerResponse;
import com.wrkspot.assessment.util.CustomPageableRequest;

public interface SearchCustomerInPort {

    CustomerResponse searchCustomer(CustomerFilters customerFilters, CustomPageableRequest customPageableRequest);

}
