package org.carbonit.jmeritte.exception;

public class LCATRuntimeException extends RuntimeException {

    final String message;

    public LCATRuntimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
