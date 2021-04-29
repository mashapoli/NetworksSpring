package com.example.mailapp.service;

import com.example.mailapp.model.exception.MessageNotFoundException;
import com.example.mailapp.model.Message;
import com.example.mailapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getMessages(){
        return StreamSupport
                .stream(messageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Message getMessage(Long id){
        return messageRepository.findById(id).orElseThrow(() ->
                new MessageNotFoundException(id));
    }

    public Message deleteMessage(Long id){
        Message message = getMessage(id);
        messageRepository.delete(message);
        return message;
    }

    @Transactional
    public Message editMessage(Long id, Message message){
        Message messageToEdit = getMessage(id);
        messageToEdit.setSubject(message.getSubject());
        messageToEdit.setText(message.getText());
        messageToEdit.setSqlTimestamp(message.getSqlTimestamp());
        return messageToEdit;
    }

}
