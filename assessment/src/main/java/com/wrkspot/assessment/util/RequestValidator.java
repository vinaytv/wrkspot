package com.wrkspot.assessment.util;

import com.wrkspot.assessment.common.data.Issues;
import com.wrkspot.assessment.controller.exception.ValidationException;
import com.wrkspot.assessment.modules.customer.domain.data.Customer;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerAddress;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    private static final String MISSING_PARAM = "Required Field Missing";

    private RequestValidator() {

    }

    public static void validateCreateCustomerRequest(List<Customer> customer) {
        List<Issues> issuesList = new ArrayList<>();

        customer.forEach(customerDetails -> {
            if (customerDetails.getCustomer() == null) {
                issuesList.add(new Issues(MISSING_PARAM, "customer object is mandatory", "body", "customer"));
            }

            if (null == customerDetails.getCustomer().getFirstName()) {
                issuesList.add(new Issues(MISSING_PARAM, "customer firstName is mandatory", "body", "firstName"));
            }
            if (null == customerDetails.getCustomer().getLastName()) {
                issuesList.add(new Issues(MISSING_PARAM, "customer lastName is mandatory", "body", "lastName"));
            }

            if (null == customerDetails.getCustomer().getAge()) {
                issuesList.add(new Issues(MISSING_PARAM, "age is mandatory", "body", "age"));
            }
            if (null == customerDetails.getCustomer().getSpendingLimit()) {
                issuesList.add(new Issues(MISSING_PARAM, "spendingLimit is mandatory", "body", "spendingLimit"));
            }
            if (null == customerDetails.getCustomer().getMobileNumber()) {
                issuesList.add(new Issues(MISSING_PARAM, "mobileNumber is mandatory", "body", "mobileNumber"));
            }

            List<CustomerAddress> addresses = customerDetails.getCustomer().getAddress();
            if (addresses == null || addresses.isEmpty()) {
                issuesList.add(new Issues(MISSING_PARAM, "address list is mandatory", "body", "address"));
            } else {
                for (int i = 0; i < addresses.size(); i++) {
                    CustomerAddress address = addresses.get(i);
                    if (null == address.getType()) {
                        issuesList.add(new Issues(MISSING_PARAM, "address type is mandatory", "body", "address[" + i + "].type"));
                    }
                    if (null == address.getAddress1()) {
                        issuesList.add(new Issues(MISSING_PARAM, "address1 is mandatory", "body", "address[" + i + "].address1"));
                    }
                    if (null == address.getAddress2()) {
                        issuesList.add(new Issues(MISSING_PARAM, "address2 is mandatory", "body", "address[" + i + "].address2"));
                    }
                    if (null == address.getCity()) {
                        issuesList.add(new Issues(MISSING_PARAM, "city is mandatory", "body", "address[" + i + "].city"));
                    }
                    if (null == address.getState()) {
                        issuesList.add(new Issues(MISSING_PARAM, "state is mandatory", "body", "address[" + i + "].state"));
                    }
                    if (null == address.getZipCode()) {
                        issuesList.add(new Issues(MISSING_PARAM, "zipCode is mandatory", "body", "address[" + i + "].zipCode"));
                    }
                }
            }

        });

        if (!issuesList.isEmpty())
            throw new ValidationException(issuesList);

    }
}
