package com.trafigura.equityposition.exception;


public class CustomException extends RuntimeException {
    protected final ErrorCodeEnum enumError;

    public CustomException(final ErrorCodeEnum enumError) {
        super(enumError.getDescription());
        this.enumError = enumError;
    }

    public ErrorCodeEnum getEnumError() {
        return enumError;
    }

}
