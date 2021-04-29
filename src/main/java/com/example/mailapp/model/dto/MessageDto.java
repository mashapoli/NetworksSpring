package com.example.mailapp.model.dto;

import com.example.mailapp.model.Message;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Basic;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class MessageDto {
    private Long id;
    private String subject;
    private String text;
    private PlainUserDto plainUserDto;
    @CreationTimestamp
    private LocalDateTime sqlTimestamp;

    public static MessageDto from(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setSubject(message.getSubject());
        messageDto.setText(message.getText());
        messageDto.setSqlTimestamp(message.getSqlTimestamp());
        if (Objects.nonNull(message.getUser())){
            messageDto.setPlainUserDto(PlainUserDto.from(message.getUser()));
        }
        return messageDto;
    }
}
