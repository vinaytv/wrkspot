package com.wrkspot.assessment.modules.customer.domain.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class CustomerFilters {

    private String name;

    private String city;

    private String state;
}
