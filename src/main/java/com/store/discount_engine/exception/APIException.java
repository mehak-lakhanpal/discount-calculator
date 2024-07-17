package com.store.discount_engine.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIException extends RuntimeException {
    private final String message;

    public APIException(String message) {
        this.message = message;
    }
}
