package com.example.mailapp.model.dto;

import com.example.mailapp.model.Message;
import com.example.mailapp.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private List<MessageDto> messageDto = new ArrayList<>();

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setMessageDto(user.getMessages().stream().map(MessageDto::from).collect(Collectors.toList()));
        return userDto;
    }
}
