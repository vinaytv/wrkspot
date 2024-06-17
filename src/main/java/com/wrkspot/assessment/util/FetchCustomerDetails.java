package com.wrkspot.assessment.util;

import com.wrkspot.assessment.modules.customer.domain.data.Customer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FetchCustomerDetails {

    public List<Customer> getOnlyInListA(List<Customer> listA, List<Customer> listB) {
        Set<Customer> setB = new HashSet<>(listB);
        return listA.stream()
                .filter(customer -> !setB.contains(customer)).toList();
    }

    public List<Customer> getOnlyInListB(List<Customer> listA, List<Customer> listB) {
        Set<Customer> setA = new HashSet<>(listA);
        return listB.stream()
                .filter(customer -> !setA.contains(customer)).toList();
    }

    public List<Customer> getInBothListAAndB(List<Customer> listA, List<Customer> listB) {
        Set<Customer> setB = new HashSet<>(listB);

        return listA.stream()
                .filter(setB::contains).toList();
    }
}
