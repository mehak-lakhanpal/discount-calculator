package com.store.discount_engine.exception;

public class BadRequestException extends APIException {

    public BadRequestException(final String message) {
        super(message);
    }

}