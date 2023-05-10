package com.ldnhat.embeddedserver.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Bad request Exception
 */
@Getter
@Setter
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    public static final String ERROR_INVALID_TOKEN = "ERROR_INVALID_TOKEN";
    public static final String ERROR_SIGN_IN_BAD_REQUEST = "ERROR_SIGN_IN_BAD_REQUEST";
    public static final String ERROR_ACCESS_TOKEN_BAD_REQUEST = "ERROR_ACCESS_TOKEN_BAD_REQUEST";
    public static final String EMAIL_IS_INVALID = "EMAIL_IS_INVALID";
    public static final String EMAIL_OR_PASSWORD_IS_INVALID = "EMAIL_OR_PASSWORD_IS_INVALID";
    public static final String USER_IS_INACTIVE = "USER_IS_INACTIVE";
    public static final String PASSWORD_INPUT_IS_INVALID = "PASSWORD_INPUT_IS_INVALID";
    public static final String PASSWORD_IS_INVALID = "PASSWORD_IS_INVALID";
    public static final String DEVICE_NAME_IS_ALREADY_EXIST = "DEVICE_NAME_IS_ALREADY_EXIST";

    private String error;
    private String message;
    private boolean isJson;
    @JsonIgnore
    private boolean isPrintStackTrace;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, boolean isJson) {
        super(message);
        this.message = message;
        this.isJson = isJson;
    }


    public BadRequestException(String error, String message, boolean isJson) {
        super(message);
        this.error = error;
        this.message = message;
        this.isJson = isJson;
    }
}
