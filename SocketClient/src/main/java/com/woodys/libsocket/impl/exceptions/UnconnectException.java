package com.woodys.libsocket.impl.exceptions;

/**
 * Created by woodys on 2017/4/16.
 */

public class UnconnectException extends RuntimeException {
    public UnconnectException() {
        super();
    }

    public UnconnectException(String message) {
        super(message);
    }

    public UnconnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnconnectException(Throwable cause) {
        super(cause);
    }

    protected UnconnectException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
