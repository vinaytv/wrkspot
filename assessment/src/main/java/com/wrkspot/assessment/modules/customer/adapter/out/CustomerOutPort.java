package com.wrkspot.assessment.modules.customer.adapter.out;

import com.wrkspot.assessment.controller.exception.GenericException;
import com.wrkspot.assessment.modules.customer.adapter.data.Address;
import com.wrkspot.assessment.modules.customer.adapter.data.CustomerEntity;
import com.wrkspot.assessment.modules.customer.adapter.service.KafkaProducerService;
import com.wrkspot.assessment.modules.customer.application.ports.out.CreateCustomerOutPort;
import com.wrkspot.assessment.modules.customer.application.ports.out.SearchCustomerOutPort;
import com.wrkspot.assessment.modules.customer.domain.data.*;
import com.wrkspot.assessment.modules.customer.util.CustomerSpecificationUtils;
import com.wrkspot.assessment.util.CustomPageableRequest;
import com.wrkspot.assessment.util.PageModelGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
public class CustomerOutPort implements SearchCustomerOutPort, CreateCustomerOutPort {

    private static final Logger logger = LoggerFactory.getLogger(CustomerOutPort.class);
    private final KafkaProducerService kafkaProducerService;
    private final CustomerRepository customerRepository;
    private final PageModelGenerator<CustomerEntity> pageModelGenerator;

    @Override
    public List<Customer> createCustomer(List<Customer> customer) {
        logger.info("creating customer {}", customer);
        List<Customer> customerList = new ArrayList<>();
        try {

            List<CustomerEntity> customerEntityList = new ArrayList<>();
            customer.forEach(customer1 -> customerEntityList.add(entityFromRequest(customer1)));
            customerRepository.saveAll(customerEntityList).forEach(
                    savedCustomer -> {
                        kafkaProducerService.sendMessage("NEW_CUSTOMER", savedCustomer);
                        customerList.add(responseFromEntity(savedCustomer));
                    }
            );
        } catch (Exception e) {
            logger.error("Error creating customer", e);
            throw new GenericException();
        }
        return customerList;
    }

    @Override
    public CustomerResponse searchCustomer(CustomerFilters customerFilters, CustomPageableRequest customPageableRequest) {
        logger.info("searching  customers with filters {}", customerFilters);

        CustomerResponse customerResponse = new CustomerResponse();
        PagedModel<CustomerEntity> pagedModel = null;
        List<Customer> customerList = new ArrayList<>();

        try {

            Specification<CustomerEntity> rewardSpecification = CustomerSpecificationUtils.withCustomerFilter(customerFilters);
            Page<CustomerEntity> customersPage = customerRepository.findAll(rewardSpecification, customPageableRequest);
            pagedModel = pageModelGenerator.getPageModel(customersPage);
            customerResponse.setTotal(customersPage.getTotalElements());
            customersPage.forEach(customerEntity -> customerList.add(responseFromEntity(customerEntity)));
            customerResponse.setItems(customerList);

            pagedModel.getLinks().forEach(link -> {
                if (link.getRel().toString().equals("prev")) {
                    customerResponse.setPrev(link.getHref());

                }
                if (link.getRel().toString().equals("first")) {
                    customerResponse.setFirst(link.getHref());

                }
                if (link.getRel().toString().equals("self")) {
                    customerResponse.setSelf(link.getHref());

                }
                if (link.getRel().toString().equals("last")) {
                    customerResponse.setLast(link.getHref());

                }
                if (link.getRel().toString().equals("next")) {
                    customerResponse.setNext(link.getHref());

                }
            });


        } catch (Exception e) {

            logger.error("Error fetching customer", e);
            throw new GenericException();

        }
        return customerResponse;
    }


    private CustomerEntity entityFromRequest(Customer customer) {

        List<Address> addressList = new ArrayList<>();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAge(customer.customer.getAge());
        customerEntity.setFirstName(customer.customer.getFirstName());
        customerEntity.setLastName(customer.customer.getLastName());
        customerEntity.setMobileNumber(customer.customer.getMobileNumber());
        customerEntity.setSpendingLimit(customer.customer.getSpendingLimit());

        customer.getCustomer().getAddress().forEach(address -> {
            Address address1 = new Address();
            address1.setAddress1(address.getAddress1());
            address1.setCity(address.getCity());
            address1.setAddress2(address.getAddress2());
            address1.setType(address.getType());
            address1.setZipCode(address.getZipCode());
            address1.setState(address.getState());
            addressList.add(address1);
        });

        customerEntity.setAddress(addressList);

        return customerEntity;
    }


    private Customer responseFromEntity(CustomerEntity customerEntity) {

        Customer customer = new Customer();
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerId(customerEntity.getCustomerId().toString());
        customerDetails.setAge(customerEntity.getAge());
        customerDetails.setMobileNumber(customerEntity.getMobileNumber());
        customerDetails.setSpendingLimit(customerEntity.getSpendingLimit());
        customerDetails.setFirstName(customerEntity.getFirstName());
        customerDetails.setLastName(customerEntity.getLastName());
        List<CustomerAddress> customerAddressList = new ArrayList<>();
        customerEntity.getAddress().forEach(address -> {
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setAddress1(address.getAddress1());
            customerAddress.setType(address.getType());
            customerAddress.setCity(address.getCity());
            customerAddress.setAddress2(address.getAddress2());
            customerAddress.setZipCode(address.getZipCode());
            customerAddress.setState(address.getState());
            customerAddressList.add(customerAddress);
        });

        customerDetails.setAddress(customerAddressList);

        customer.setCustomer(customerDetails);
        return customer;
    }
}
