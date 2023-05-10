package com.ldnhat.embeddedserver.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class APIResponseError {
    public long timestamp;
    public int code;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public String error;
    public Object message;


    public APIResponseError() {
    }

    public APIResponseError(int code, Object message) {
        this.timestamp = new Date().getTime();
        this.code = code;
        this.message = message;
    }

    public APIResponseError(int code, Object message, String error) {
        this.timestamp = new Date().getTime();
        this.code = code;
        this.message = message;
    }

    public APIResponseError(long timestamp, int code, String error, Object message) {
        this.timestamp = new Date().getTime();
        this.code = code;
        this.error = error;
        this.message = message;
    }
}
