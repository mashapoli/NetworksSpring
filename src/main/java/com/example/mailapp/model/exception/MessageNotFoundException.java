package com.example.mailapp.model.exception;

import java.text.MessageFormat;

public class MessageNotFoundException extends RuntimeException{
    public MessageNotFoundException(final Long id){
        super(MessageFormat.format("Could not find message id: {0}" , id));
    }
}