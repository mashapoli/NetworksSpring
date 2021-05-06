package com.example.bootsecurity.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Message {
    private @Id @GeneratedValue Long id;
    private String subject;
    private String text;
}
