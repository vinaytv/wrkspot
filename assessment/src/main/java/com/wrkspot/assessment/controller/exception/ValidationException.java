package com.wrkspot.assessment.controller.exception;

import com.wrkspot.assessment.common.data.ErrorResponse;
import com.wrkspot.assessment.common.data.Issues;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private final List<Issues> issuesList;
    private ErrorResponse errorResponse;


    public ValidationException(List<Issues> issuesList) {
        this.issuesList = issuesList;
        this.errorResponse = new ErrorResponse();
        errorResponse.setErrorDetails("Invalid or missing Required params.");
        errorResponse.setIssues(issuesList);

    }
}
