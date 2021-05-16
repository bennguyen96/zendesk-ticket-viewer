package com.bennguyen96;

public class InvalidTicketException extends Throwable {
    public InvalidTicketException(String message) {
        super(message);
    }
}
