package com.example.mailapp.service;

import com.example.mailapp.model.exception.MessagesAlreadyAssignedException;
import com.example.mailapp.model.exception.UserNotFoundException;

import com.example.mailapp.model.Message;
import com.example.mailapp.model.User;
import com.example.mailapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MessageService messageService;

    @Autowired
    public UserService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
    }

    public User deleteUser(Long id){
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }

    @Transactional
    public User editUser(Long id, User user){
        User userToEdit = getUser(id);
        userToEdit.setName(user.getName());
        userToEdit.setPassword(user.getPassword());
        return userToEdit;
    }

    @Transactional
    public User addMessageToUser(Long userId, Long messageId){
        User user = getUser(userId);
        Message message = messageService.getMessage(messageId);
//        if(Objects.nonNull(message.getUser())){
//            throw new MessagesAlreadyAssignedException(messageId,
//                    message.getUser().getId());
//        }
        user.addMessage(message);
        message.setUser(user);
        return user;
    }

    @Transactional
    public  User removeMessageFromUser(Long userId, Long messageId){
        User user = getUser(userId);
        Message message = messageService.getMessage(messageId);
        user.removeMessage(message);
        return user;
    }
}
