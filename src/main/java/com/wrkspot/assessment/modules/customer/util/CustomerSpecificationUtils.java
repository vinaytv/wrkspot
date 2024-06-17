package com.wrkspot.assessment.modules.customer.util;

import com.wrkspot.assessment.modules.customer.adapter.data.Address;
import com.wrkspot.assessment.modules.customer.adapter.data.CustomerEntity;
import com.wrkspot.assessment.modules.customer.domain.data.CustomerFilters;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecificationUtils {
    private CustomerSpecificationUtils() {

    }



    public static Specification<CustomerEntity> withCustomerFilter(CustomerFilters customerFilters) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (null != customerFilters.getCity()) {
                Join<CustomerEntity, Address> customer = root.join("address");
                predicates.add(criteriaBuilder.equal(customer.get("city"), customerFilters.getCity()));
            }

            if (null != customerFilters.getName()) {
                predicates.add(criteriaBuilder.equal(root.get("name"), customerFilters.getName()));
            }


            if (null != customerFilters.getState()) {
                Join<CustomerEntity, Address> customer = root.join("address");

                predicates.add(criteriaBuilder.equal(customer.get("state"), customerFilters.getState()));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
