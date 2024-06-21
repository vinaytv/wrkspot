package com.wrkspot.assessment.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class CustomerFilters {

    private String firstName;

    private String city;

    private String state;
}
