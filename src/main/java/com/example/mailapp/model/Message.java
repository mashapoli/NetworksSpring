package com.example.mailapp.model;

import com.example.mailapp.model.dto.MessageDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String text;
    @CreationTimestamp
    private LocalDateTime sqlTimestamp;
    @ManyToOne
    private User user;
//    @ManyToOne
//    private User recipient;

    public static Message from(MessageDto messageDto){
        Message message = new Message();
        message.setSubject(messageDto.getSubject());
        message.setText(messageDto.getText());
        message.setSqlTimestamp(messageDto.getSqlTimestamp());
        return message;
    }
}
