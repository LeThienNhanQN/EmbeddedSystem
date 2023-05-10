package com.ldnhat.embeddedserver.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * NotFound Exception
 */
@Getter
@Setter
public class NotFoundException extends Exception {

    public static final String ERROR_NOT_FOUND = "ERROR_NOT_FOUND";
    public static final String ERROR_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND";
    public static final String ERROR_DATA_NOT_FOUND = "ERROR_DATA_NOT_FOUND";
    public static final String ERROR_DEVICE_NOT_FOUND = "ERROR_DEVICE_NOT_FOUND";

    private static final long serialVersionUID = 1L;
    private String error;
    private String message;
    private HttpStatus httpStatus;
    @JsonIgnore
    private boolean isPrintStackTrace;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public NotFoundException(String error, String message, HttpStatus httpStatus) {
        super(message);
        this.error = error;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
