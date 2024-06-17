package com.wrkspot.assessment.controller.exception;

import com.wrkspot.assessment.common.data.ErrorResponse;
import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private ErrorResponse errorResponse;

    public GenericException() {
        errorResponse = new ErrorResponse();
        errorResponse.setErrorDetails("Unable to serve your request, pls try again after sometime.");
    }

}
