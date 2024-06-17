package com.wrkspot.assessment.common.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private String errorDetails;
    private List<Issues> issues;
}
