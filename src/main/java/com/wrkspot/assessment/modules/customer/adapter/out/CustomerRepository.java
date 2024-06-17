package com.wrkspot.assessment.modules.customer.adapter.out;

import com.wrkspot.assessment.modules.customer.adapter.data.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, String>, JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {


}