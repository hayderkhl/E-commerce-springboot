package com.example.ecommercespringboot.exception;

public enum ErrorCodes {

    CUSTOMER_NOT_VALID(1000),
    CUSTOMER_NOT_FOUND(1001),
    EMAIL_ALREADY_EXIST(1002),
    SUBCATEGORY_NOT_VALID(1003),
    CATEGORY_NOT_VALID(1004),
    CATEGORY_DOES_NOT_EXIST(1005),
    CATEGORY_NOT_FOUND(1006), Sub_CATEGORY_NOT_FOUND(1007), 
    PRODUCT_NOT_VALID(1008), PRODUCT_NOT_FOUND(1009), IMAGE_FILE_NOT_VALID(1010)
    , NOT_AUTHORIZED(1011);
    private int code;
    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
