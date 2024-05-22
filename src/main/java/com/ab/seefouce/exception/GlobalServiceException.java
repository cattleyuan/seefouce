package com.ab.seefouce.exception;

import com.ab.seefouce.common.enums.GlobalServiceStatusCode;
import lombok.Getter;

/**
 * @author cattleyuan
 */
@Getter
public class GlobalServiceException extends RuntimeException{

    private final GlobalServiceStatusCode statusCode;

    private final String message;

    public GlobalServiceException(String message, GlobalServiceStatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public GlobalServiceException(String message) {
        this.message = message;
        this.statusCode = GlobalServiceStatusCode.SYSTEM_SERVICE_FAIL;
    }

    public GlobalServiceException(GlobalServiceStatusCode statusCode) {
        this.message = statusCode.getMessage();
        this.statusCode = statusCode;
    }

    public GlobalServiceException() {
        this.message = GlobalServiceStatusCode.SYSTEM_SERVICE_FAIL.getMessage();
        this.statusCode = GlobalServiceStatusCode.SYSTEM_SERVICE_FAIL;
    }
}
