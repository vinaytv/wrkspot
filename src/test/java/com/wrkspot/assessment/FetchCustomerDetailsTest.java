package com.wrkspot.assessment;

import com.wrkspot.assessment.modules.customer.domain.data.Customer;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerDetails;
import com.wrkspot.assessment.util.FetchCustomerDetails;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FetchCustomerDetailsTest extends Base {

    @Autowired
    FetchCustomerDetails fetchCustomerDetails;
    List<Customer> listA;
    List<Customer> listB;

    @BeforeEach
    public void setup() {

        listA = Instancio.createList(Customer.class);
        listB = Instancio.createList(Customer.class);

    }

    @Test
    void getInBothListAAndBTest() {
        Customer customer = new Customer();
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAge(10);
        customerDetails.setLastName("LasteName");
        customerDetails.setFirstName("FirstName");
        customerDetails.setMobileNumber("122222");
        customerDetails.setSpendingLimit(1000.00);
        customer.setCustomer(customerDetails);
        List<Customer> customerlistA = new ArrayList<>();
        customerlistA.add(customer);
        List<Customer> customerlistB = new ArrayList<>();
        customerlistB.add(customer);
        List<Customer> inboth = fetchCustomerDetails.getInBothListAAndB(customerlistA, customerlistB);
        assertEquals(inboth, customerlistB);


    }

    @Test
    void getOnlyInListBtest() {
        assertEquals(listB, fetchCustomerDetails.getOnlyInListB(listA, listB));
    }

    @Test
    void getOnlyInListAtest() {
        assertEquals(listA, fetchCustomerDetails.getOnlyInListA(listA, listB));
    }
}
