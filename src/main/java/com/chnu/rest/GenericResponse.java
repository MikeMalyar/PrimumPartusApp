package com.chnu.rest;

import java.util.Map;

public class GenericResponse<T> {

    private T result;
    private boolean success;
    private String message;
    private int resultCode;
    private Map<String, Object> additionalInformation;

    public static <T> GenericResponse<T> of(T result) {
        return new GenericResponse<T>().setResult(result).setSuccess(true);
    }

    public static <T> GenericResponse<T> error(String msg) {
        return new GenericResponse<T>().setSuccess(false).setMessage(msg);
    }

    public static <T> GenericResponse<T> withSuccessMessage(String msg) {
        return new GenericResponse<T>().setSuccess(true).setMessage(msg);
    }

    public T getResult() {
        return result;
    }

    public GenericResponse<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public GenericResponse<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public GenericResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getResultCode() {
        return resultCode;
    }

    public GenericResponse<T> setResultCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public GenericResponse<T> setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
