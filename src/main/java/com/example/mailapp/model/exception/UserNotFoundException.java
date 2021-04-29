package com.example.mailapp.model.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(final Long id){
        super(MessageFormat.format("Could not find user id: {0}" , id));
    }
}
