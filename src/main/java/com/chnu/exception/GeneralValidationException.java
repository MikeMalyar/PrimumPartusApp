package com.chnu.exception;

import java.util.List;

public class GeneralValidationException extends RuntimeException {

    private List<String> validationErrors;
    private Class<?> clazz;

    public GeneralValidationException(String message) {
        super(message);
    }

    public GeneralValidationException(String message, List<String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public GeneralValidationException setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public GeneralValidationException setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }
}
