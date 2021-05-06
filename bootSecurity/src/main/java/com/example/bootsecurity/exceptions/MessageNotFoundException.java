package com.example.bootsecurity.exceptions;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
