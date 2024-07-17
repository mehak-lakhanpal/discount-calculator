package com.store.discount_engine.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIException extends RuntimeException {
    private String message;

    public APIException(String message) {
        this.message=message;
    }

    public APIException() {

    }
}
