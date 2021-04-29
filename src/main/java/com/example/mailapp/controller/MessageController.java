package com.example.mailapp.controller;

import com.example.mailapp.model.Message;
import com.example.mailapp.model.dto.MessageDto;
import com.example.mailapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> addMessage(@RequestBody final MessageDto messageDto){
        Message message = messageService.addMessage(Message.from(messageDto));
        return new ResponseEntity<>(MessageDto.from(message), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessage(){
        List<Message> messages = messageService.getMessages();
        List<MessageDto> messageDto = messages.stream().map(MessageDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable final Long id){
        Message message = messageService.getMessage(id);
        return new ResponseEntity<>(MessageDto.from(message), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<MessageDto> deleteMessage(@PathVariable final Long id){
        Message message = messageService.deleteMessage(id);
        return new ResponseEntity<>(MessageDto.from(message), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<MessageDto> editMessage(@PathVariable final Long id, @RequestBody final MessageDto messageDto){
        Message editMessage = messageService.editMessage(id, Message.from(messageDto));
        return new ResponseEntity<>(MessageDto.from(editMessage), HttpStatus.OK);
    }
}
