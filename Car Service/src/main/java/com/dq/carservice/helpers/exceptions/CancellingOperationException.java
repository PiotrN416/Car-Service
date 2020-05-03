package com.dq.carservice.helpers.exceptions;

public class CancellingOperationException extends CarServiceUncheckedException {

    public CancellingOperationException(String message) {
        super(message);
    }
}
