package com.example.bootsecurity.conroller;

import com.example.bootsecurity.domain.Message;
import com.example.bootsecurity.exceptions.MessageNotFoundException;
import com.example.bootsecurity.repos.MessageRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/")
class MessageController {

    private final MessageRepository repository;
    MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    String hello(){
        return "hi";
    }

    @GetMapping("/messages")
    List<Message> all() {
        return repository.findAll();
    }

    @PostMapping("/messages")
    Message newMessage(@RequestBody Message newMessage) {
        return repository.save(newMessage);
    }

    @GetMapping("/messages/{id}")
    Message one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
