package com.trafigura.equityposition.exception;

import org.thymeleaf.util.StringUtils;


public enum ErrorCodeEnum {
    SAME_VERSION_ERROR("001", "INSERT / UPDATE / CANCEL are actions on a Trade (with same trade id but different version)"),
    INSERT_CANCEL_ERROR("002", "INSERT will always be 1st version of a Trade, CANCEL will always be last version of Trade"),
    INSERT__VERSION_ERROR("003", "INSERT will always be 1st ,version must be 1"),
    UPDATE_ERROR("004", "for UPDATE, but SecurityCode or Quantity or Buy/Sell has no change"),
    INSERT_DATA_ERROR("005", "for UPDATE ,but SecurityCode or Quantity or Buy/Sell has no change");

    private final String code;
    private final String description;

    private ErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
