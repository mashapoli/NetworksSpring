package com.example.bootsecurity.repos;

import com.example.bootsecurity.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}