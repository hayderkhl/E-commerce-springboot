package com.example.ecommercespringboot.exception;

public enum ErrorCodes {

    CUSTOMER_NOT_VALID(1000),
    CUSTOMER_NOT_FOUND(1001),
    EMAIL_ALREADY_EXIST(1002);

    private int code;
    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
