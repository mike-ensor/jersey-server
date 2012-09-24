package com.clickconcepts.jersey.server.model;

public class MyException extends RuntimeException {
    private final long messageCode;
    private final String message;

    public MyException(long messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.message = message;
    }

    public long getMessageCode() {
        return messageCode;
    }

    public String getMessage() {
        return message;
    }
}
