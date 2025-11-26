package com.mystore.store_api.exception;

import java.util.List;

public class BusinessException extends RuntimeException {

    private final List<String> errors;

    public BusinessException(List<String> errors) {
        super("Validation error.");
        this.errors = errors;
    }

    public BusinessException(String error) {
        super(error);
        this.errors = List.of(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
