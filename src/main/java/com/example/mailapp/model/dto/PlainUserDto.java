package com.example.mailapp.model.dto;

import com.example.mailapp.model.User;
import lombok.Data;

@Data
public class PlainUserDto {
    private Long id;
    private String name;
    private String password;

    public static PlainUserDto from(User user){
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setName(user.getName());
        plainUserDto.setPassword(user.getPassword());
        return plainUserDto;
    }
}