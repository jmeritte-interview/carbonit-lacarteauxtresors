package org.carbonit.jmeritte.exception;

public class LCATException extends Exception {

    final String message;

    public LCATException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
