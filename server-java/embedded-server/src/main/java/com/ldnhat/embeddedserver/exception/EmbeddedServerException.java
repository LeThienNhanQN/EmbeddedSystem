package com.ldnhat.embeddedserver.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class EmbeddedServerException extends Exception {
    private int code;
    private String error;
    private String message;
    private HttpStatus httpStatus;
    @JsonIgnore
    private boolean isPrintStackTrace;

    public static final String ERROR_JWT_TOKEN_EXPIRED = "ERROR_JWT_TOKEN_EXPIRED";
    public static final String ERROR_JWT_INVALID_TOKEN = "ERROR_JWT_INVALID_TOKEN";
    public static final String ERROR_UNKNOWN = "ERROR_UNKNOWN";

    public EmbeddedServerException() {

    }

    public EmbeddedServerException(int code, String error, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.error = error;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
