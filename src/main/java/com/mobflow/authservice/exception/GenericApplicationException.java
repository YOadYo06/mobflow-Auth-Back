package com.mobflow.authservice.exception;

import com.mobflow.authservice.model.enums.ErrorTP;

public class GenericApplicationException extends RuntimeException {
    public GenericApplicationException(ErrorTP error) {
        super(String.valueOf(error));
    }
}
