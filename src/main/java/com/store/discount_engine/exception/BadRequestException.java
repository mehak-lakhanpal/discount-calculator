package com.store.discount_engine.exception;

import lombok.Data;

@Data
public class BadRequestException extends APIException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(final String message) {
        super(message);
    }

}