package com.example.mailapp.controller;

import com.example.mailapp.model.User;
import com.example.mailapp.model.dto.UserDto;
import com.example.mailapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "addUser")
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody final UserDto userDto){
        User user = userService.addUser(User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @Operation(summary = "getUsers")
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDto> userDto = users.stream().map(UserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(summary = "getUser")
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable final Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @Operation(summary = "deleteUser")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable final Long id){
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @Operation(summary = "editUser")
    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable final Long id, @RequestBody final UserDto userDto){
        User editUser = userService.editUser(id, User.from(userDto));
        return new ResponseEntity<>(UserDto.from(editUser), HttpStatus.OK);
    }

    @Operation(summary = "addMessageToUser")
    @PostMapping(value = "{userId}/messages/{messageId}/add")
    public ResponseEntity<UserDto> addMessageToUser(@PathVariable final Long userId, @PathVariable final Long messageId){
        User user = userService.addMessageToUser(userId, messageId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @Operation(summary = "removeMessageToUser")
    @DeleteMapping(value = "{userId}/messages/{messageId}/add")
    public ResponseEntity<UserDto> removeMessageToUser(@PathVariable final Long userId, @PathVariable final Long messageId){
        User user = userService.removeMessageFromUser(userId, messageId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }
}
