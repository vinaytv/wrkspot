package com.wrkspot.assessment.modules.customer.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    List<Customer> items;
    private String query;
    private String first;
    private String prev;
    private String next;
    private String last;
    private String self;
    private Long total;


}
