package com.wrkspot.assessment.modules.customer.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wrkspot.assessment.Base;
import com.wrkspot.assessment.modules.customer.domain.data.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CustomerControllerTest extends Base {



    @Test
    public void createCustomerTest200() throws Exception {

        List<Customer> customerList= Instancio.createList(Customer.class);
        customerList.forEach(customer -> {
            customer.getCustomer().setCustomerId(null);
        });
        String json=createRequestJsonString(customerList);
        mockMvc.perform(post("/api/v1/customer").content(json).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
    }

    @Test
    public void createCustomerTest400() throws Exception {

        List<Customer> customerList= Instancio.createList(Customer.class);
        customerList.forEach(customer -> {
            customer.getCustomer().setCustomerId(null);
            customer.getCustomer().setFirstName(null);
        });
        String json=createRequestJsonString(customerList);
        mockMvc.perform(post("/api/v1/customer").content(json).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
    }



}
