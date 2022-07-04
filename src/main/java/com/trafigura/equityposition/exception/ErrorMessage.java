package com.trafigura.equityposition.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private static final long serialVersionUID = 8065583911104112360L;
    private String errorCode;
    private String errorMessage;

    public ErrorMessage() {
        super();
    }

}
