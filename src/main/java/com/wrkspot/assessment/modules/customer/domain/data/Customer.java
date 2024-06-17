package com.wrkspot.assessment.modules.customer.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class Customer {

    @JsonProperty("customer")
    public CustomerDetails customer;

}
