package com.wrkspot.assessment.modules.customer.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDetails {
    @JsonProperty("customerId")
    public String customerId;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("age")
    public Integer age;
    @JsonProperty("spendingLimit")
    public Double spendingLimit;
    @JsonProperty("mobileNumber")
    public String mobileNumber;
    @JsonProperty("address")
    public List<CustomerAddress> address;
}
