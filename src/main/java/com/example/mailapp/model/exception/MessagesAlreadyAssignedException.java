package com.example.mailapp.model.exception;

import java.text.MessageFormat;

public class MessagesAlreadyAssignedException extends RuntimeException{
    public MessagesAlreadyAssignedException(final Long messageId, final Long userId){
        super(MessageFormat.format("Item: {0} is already assigned to user: {1}", messageId, userId));
    }
}
